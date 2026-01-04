package com.finaya.wallete.infrastructure.persistence.entity;

import com.finaya.wallete.domain.enums.WalletMovementType;
import com.finaya.wallete.domain.enums.CurrencyType;
import com.finaya.wallete.domain.enums.BalanceOperation;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.type.PostgreSQLEnumJdbcType;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "wallet_ledger")
public class WalletLedgerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "wallet_id", nullable = false)
    private Long walletId;

    @Column(name = "wallet_code", nullable = false, updatable = false)
    private UUID walletCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "balance_operation", nullable = false, length = 30)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    private BalanceOperation balanceOperation;

    @Column(name = "amount", nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency", nullable = false, length = 3)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    private CurrencyType currency;

    @Enumerated(EnumType.STRING)
    @Column(name = "movement_type", length = 30)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    private WalletMovementType movementType;

    @Column(name = "event_id")
    private UUID eventId;

    @Column(name = "occurred_at", nullable = false)
    private Instant occurredAt;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWalletId() {
        return walletId;
    }

    public void setWalletId(Long walletId) {
        this.walletId = walletId;
    }

    public UUID getWalletCode() {
        return walletCode;
    }

    public void setWalletCode(UUID walletCode) {
        this.walletCode = walletCode;
    }

    public BalanceOperation getBalanceOperation() {
        return balanceOperation;
    }

    public void setBalanceOperation(BalanceOperation balanceOperation) {
        this.balanceOperation = balanceOperation;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public CurrencyType getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyType currency) {
        this.currency = currency;
    }

    public WalletMovementType getMovementType() {
        return movementType;
    }

    public void setMovementType(WalletMovementType movementType) {
        this.movementType = movementType;
    }

    public UUID getEventId() {
        return eventId;
    }

    public void setEventId(UUID eventId) {
        this.eventId = eventId;
    }

    public Instant getOccurredAt() {
        return occurredAt;
    }

    public void setOccurredAt(Instant occurredAt) {
        this.occurredAt = occurredAt;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
