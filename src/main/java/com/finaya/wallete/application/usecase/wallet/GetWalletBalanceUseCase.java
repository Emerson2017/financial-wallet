package com.finaya.wallete.application.usecase.wallet;

import com.finaya.wallete.application.port.out.WalletLedgerRepositoryPort;
import com.finaya.wallete.application.port.out.WalletRepositoryPort;
import com.finaya.wallete.domain.model.Wallet;
import com.finaya.wallete.infrastructure.dto.response.BalanceResponse;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;

public class GetWalletBalanceUseCase {

    private final WalletRepositoryPort walletRepositoryPort;
    private final WalletLedgerRepositoryPort walletLedgerRepositoryPort;

    public GetWalletBalanceUseCase(WalletRepositoryPort walletRepositoryPort, WalletLedgerRepositoryPort walletLedgerRepositoryPort) {
        this.walletRepositoryPort = walletRepositoryPort;
        this.walletLedgerRepositoryPort = walletLedgerRepositoryPort;
    }

    public BalanceResponse execute(Long walletId, Instant at) {
        Optional<Wallet> walletOp = walletRepositoryPort.findById(walletId);

        if (walletOp.isEmpty()) {
            throw new RuntimeException("Wallet Not Found.");
        }

        Instant effectiveAt = at != null ? at : Instant.now();
        BigDecimal balance = walletLedgerRepositoryPort.calculateBalance(walletId, effectiveAt);

        Wallet wallet = walletOp.get();

        return new BalanceResponse(balance, wallet.getCurrency());
    }
}
