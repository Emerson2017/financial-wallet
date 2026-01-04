package com.finaya.wallete.application.port.out;

import com.finaya.wallete.domain.model.WalletLedger;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;

public interface WalletLedgerRepositoryPort {
    Optional<WalletLedger> findById(Long id);
    WalletLedger save(WalletLedger wallet);
    BigDecimal calculateBalance(Long walletId, Instant at);
}
