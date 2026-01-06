package com.finaya.wallete.application.usecase.wallet;

import com.finaya.wallete.application.port.out.WalletLedgerRepositoryPort;
import com.finaya.wallete.application.port.out.WalletRepositoryPort;
import com.finaya.wallete.domain.exception.EntityNotFoundException;
import com.finaya.wallete.domain.model.Wallet;
import com.finaya.wallete.infrastructure.dto.response.BalanceResponse;

import java.math.BigDecimal;
import java.time.Instant;

public class GetWalletBalanceUseCase {

    private final WalletRepositoryPort walletRepositoryPort;
    private final WalletLedgerRepositoryPort walletLedgerRepositoryPort;

    public BalanceResponse execute(Long walletId, Instant at) {

        Wallet wallet = walletRepositoryPort.findById(walletId)
                .orElseThrow(() -> new EntityNotFoundException("Wallet Not Found"));

        Instant effectiveAt = at != null ? at : Instant.now();
        BigDecimal balance = walletLedgerRepositoryPort.calculateBalance(walletId, effectiveAt);

        return new BalanceResponse(balance, wallet.getCurrency());
    }

    public GetWalletBalanceUseCase(WalletRepositoryPort walletRepositoryPort, WalletLedgerRepositoryPort walletLedgerRepositoryPort) {
        this.walletRepositoryPort = walletRepositoryPort;
        this.walletLedgerRepositoryPort = walletLedgerRepositoryPort;
    }
}
