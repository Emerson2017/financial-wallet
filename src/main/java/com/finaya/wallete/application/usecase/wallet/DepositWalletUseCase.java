package com.finaya.wallete.application.usecase.wallet;

import com.finaya.wallete.application.port.out.WalletLedgerRepositoryPort;
import com.finaya.wallete.application.port.out.WalletRepositoryPort;
import com.finaya.wallete.application.usecase.pix.CreatePixTransferUseCase;
import com.finaya.wallete.domain.enums.WalletMovementType;
import com.finaya.wallete.domain.exception.EntityNotFoundException;
import com.finaya.wallete.domain.model.Wallet;
import com.finaya.wallete.domain.model.WalletLedger;
import com.finaya.wallete.infrastructure.dto.request.DepositWalletRequest;
import com.finaya.wallete.infrastructure.dto.response.DepositWalletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

public class DepositWalletUseCase {

    private static final Logger logger = LoggerFactory.getLogger(DepositWalletUseCase.class);

    private final WalletRepositoryPort walletRepository;
    private final WalletLedgerRepositoryPort walletLedgerRepository;


    public DepositWalletUseCase(WalletRepositoryPort walletRepository, WalletLedgerRepositoryPort walletLedgerRepository) {
        this.walletRepository = walletRepository;
        this.walletLedgerRepository = walletLedgerRepository;
    }

    @Transactional
    public DepositWalletResponse execute(Long walletId, DepositWalletRequest request) {

        Wallet wallet = walletRepository.findByIdWithLock(walletId)
                .orElseThrow(() -> new EntityNotFoundException("Wallet Not Found"));

        WalletLedger ledger = wallet.credit(request.amount(), WalletMovementType.DEPOSIT);
        ledger = walletLedgerRepository.save(ledger);

        logger.info("Deposit completed successfully: [Amount: {} | EventId: {} | fromWallet: {}]",
                request.amount(), ledger.getEventId(), wallet.getCode());

        return new DepositWalletResponse(
                wallet.getCode(),
                ledger.getEventId(),
                request.amount(),
                wallet.getCurrency()
        );
    }
}
