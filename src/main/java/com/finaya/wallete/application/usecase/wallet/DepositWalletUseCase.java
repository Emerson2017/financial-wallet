package com.finaya.wallete.application.usecase.wallet;

import com.finaya.wallete.application.port.out.WalletLedgerRepositoryPort;
import com.finaya.wallete.application.port.out.WalletRepositoryPort;
import com.finaya.wallete.domain.enums.WalletMovementType;
import com.finaya.wallete.domain.model.Wallet;
import com.finaya.wallete.domain.model.WalletLedger;
import com.finaya.wallete.infrastructure.dto.request.DepositWalletRequest;
import com.finaya.wallete.infrastructure.dto.response.DepositWalletResponse;

import java.util.Optional;

public class DepositWalletUseCase {

    private final WalletRepositoryPort walletRepository;
    private final WalletLedgerRepositoryPort walletLedgerRepository;


    public DepositWalletUseCase(WalletRepositoryPort walletRepository, WalletLedgerRepositoryPort walletLedgerRepository) {
        this.walletRepository = walletRepository;
        this.walletLedgerRepository = walletLedgerRepository;
    }

    public DepositWalletResponse execute(Long idWallet, DepositWalletRequest request) {

        Optional<Wallet> walletOp = walletRepository.findById(idWallet);

        if (walletOp.isEmpty()) {
            throw new RuntimeException("Wallet Not Found.");
        }

        Wallet wallet = walletOp.get();
        WalletLedger ledger = wallet.credit(request.amount(), WalletMovementType.DEPOSIT);
        ledger = walletLedgerRepository.save(ledger);

        return new DepositWalletResponse(
                wallet.getCode(),
                ledger.getEventId(),
                request.amount(),
                wallet.getCurrency()
        );
    }
}
