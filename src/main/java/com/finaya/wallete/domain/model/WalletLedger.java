package com.finaya.wallete.domain.model;

import com.finaya.wallete.domain.enums.CurrencyType;
import com.finaya.wallete.domain.enums.WalletMovementType;
import com.finaya.wallete.domain.enums.BalanceOperation;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class WalletLedger {

    private final Long walletId;
    private final UUID walletCode;
    private final BalanceOperation balanceOperation;
    private final BigDecimal amount;
    private final CurrencyType currency;
    private final WalletMovementType movementType;
    private final UUID eventId;
    private final Instant occurredAt;
    private final Instant createdAt;

    public WalletLedger(Long walletId,
                        UUID walletCode,
                        BalanceOperation balanceOperation,
                        BigDecimal amount,
                        CurrencyType currency,
                        WalletMovementType movementType,
                        UUID eventId,
                        Instant occurredAt,
                        Instant createdAt) {
        this.walletId = walletId;
        this.walletCode = walletCode;
        this.balanceOperation = balanceOperation;
        this.amount = amount;
        this.currency = currency;
        this.movementType = movementType;
        this.eventId = eventId;
        this.occurredAt = occurredAt;
        this.createdAt = createdAt;
    }

    public static WalletLedger credit(Wallet wallet,
                                      BigDecimal amount,
                                      WalletMovementType movementType) {
        return new WalletLedger(
                wallet.getId(),
                UUID.randomUUID(),
                BalanceOperation.CREDIT,
                amount,
                CurrencyType.BRL,
                movementType,
                UUID.randomUUID(),
                Instant.now(),
                Instant.now());
    }

    public static WalletLedger debit(Wallet wallet,
                                     BigDecimal amount,
                                     WalletMovementType movementType) {
        return new WalletLedger(
                wallet.getId(),
                UUID.randomUUID(),
                BalanceOperation.DEBIT,
                amount,
                CurrencyType.BRL,
                movementType,
                UUID.randomUUID(),
                Instant.now(),
                Instant.now());
    }

    public Long getWalletId() {
        return walletId;
    }

    public UUID getWalletCode() {
        return walletCode;
    }

    public BalanceOperation getBalanceOperation() {
        return balanceOperation;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public CurrencyType getCurrency() {
        return currency;
    }

    public WalletMovementType getMovementType() {
        return movementType;
    }

    public UUID getEventId() {
        return eventId;
    }

    public Instant getOccurredAt() {
        return occurredAt;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
