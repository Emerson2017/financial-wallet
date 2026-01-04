package com.finaya.wallete.application.usecase.wallet;

import com.finaya.wallete.application.port.out.WalletLedgerRepositoryPort;
import com.finaya.wallete.application.port.out.WalletRepositoryPort;
import com.finaya.wallete.domain.enums.WalletMovementType;
import com.finaya.wallete.domain.model.Wallet;
import com.finaya.wallete.domain.model.WalletLedger;
import com.finaya.wallete.infrastructure.dto.request.WithdrawWalletRequest;
import com.finaya.wallete.infrastructure.dto.response.WithdrawWalletResponse;

import java.util.Optional;

public class WithdrawWalletUseCase {

    private final WalletRepositoryPort walletRepository;
    private final WalletLedgerRepositoryPort walletLedgerRepository;

    public WithdrawWalletUseCase(WalletRepositoryPort walletRepository, WalletLedgerRepositoryPort walletLedgerRepository) {
        this.walletRepository = walletRepository;
        this.walletLedgerRepository = walletLedgerRepository;
    }

    public WithdrawWalletResponse execute(Long walletId, WithdrawWalletRequest request) {
        Optional<Wallet> walletOp = walletRepository.findById(walletId);

        if (walletOp.isEmpty()) {
            throw new RuntimeException("Wallet Not Found.");
        }

        Wallet wallet = walletOp.get();
        WalletLedger ledger = wallet.debit(request.amount(), WalletMovementType.WITHDRAW);
        ledger = walletLedgerRepository.save(ledger);
        return new WithdrawWalletResponse(
                wallet.getCode(),
                ledger.getEventId(),
                request.amount(),
                wallet.getCurrency());
    }
}
