package com.finaya.wallete.application.usecase.wallet;

import com.finaya.wallete.application.port.out.WalletLedgerRepositoryPort;
import com.finaya.wallete.application.port.out.WalletRepositoryPort;
import com.finaya.wallete.domain.enums.WalletMovementType;
import com.finaya.wallete.domain.exception.EntityNotFoundException;
import com.finaya.wallete.domain.exception.InsufficientBalanceException;
import com.finaya.wallete.domain.model.Wallet;
import com.finaya.wallete.domain.model.WalletLedger;
import com.finaya.wallete.infrastructure.dto.request.WithdrawWalletRequest;
import com.finaya.wallete.infrastructure.dto.response.WithdrawWalletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;

public class WithdrawWalletUseCase {

    private static final Logger logger = LoggerFactory.getLogger(WithdrawWalletUseCase.class);

    private final WalletRepositoryPort walletRepository;
    private final WalletLedgerRepositoryPort walletLedgerRepository;

    public WithdrawWalletUseCase(WalletRepositoryPort walletRepository, WalletLedgerRepositoryPort walletLedgerRepository) {
        this.walletRepository = walletRepository;
        this.walletLedgerRepository = walletLedgerRepository;
    }

    @Transactional
    public WithdrawWalletResponse execute(Long walletId, WithdrawWalletRequest request) {
        Wallet wallet = walletRepository.findByIdWithLock(walletId)
                .orElseThrow(() -> new EntityNotFoundException("Wallet Not Found"));

        if (!canDebitWallet(wallet, request.amount())) {
            throw new InsufficientBalanceException();
        }

        WalletLedger ledger = wallet.debit(request.amount(), WalletMovementType.WITHDRAW);
        ledger = walletLedgerRepository.save(ledger);

        logger.info("Withdraw completed successfully: [Amount: {} | EventId: {} | fromWallet: {}]",
                request.amount(), ledger.getEventId(), wallet.getCode());

        return new WithdrawWalletResponse(
                wallet.getCode(),
                ledger.getEventId(),
                request.amount(),
                wallet.getCurrency());
    }

    private boolean canDebitWallet(Wallet wallet, BigDecimal amount) {
        BigDecimal balance = walletLedgerRepository
                .calculateBalance(wallet.getId(), Instant.now());

        return balance.compareTo(amount) >= 0;
    }
}
