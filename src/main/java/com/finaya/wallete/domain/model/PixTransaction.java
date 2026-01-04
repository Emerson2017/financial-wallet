package com.finaya.wallete.domain.model;

import com.finaya.wallete.domain.enums.CurrencyType;
import com.finaya.wallete.domain.enums.PixTransactionStatus;
import com.finaya.wallete.domain.exception.InvalidPixTransactionStatusException;
import com.finaya.wallete.domain.util.PixUtil;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class PixTransaction {

    private Long id;
    private final String endToEndId;
    private final UUID idempotencyKey;

    private final Long fromWalletId;
    private final Long toWalletId;
    private final String toPixKey;
    private final BigDecimal amount;
    private final CurrencyType currency;

    private final Instant createdAt;
    private Instant processedAt;
    private PixTransactionStatus status;

    public PixTransaction(
            Long id,
            String endToEndId,
            UUID idempotencyKey,
            Long fromWalletId,
            Long toWalletId,
            String toPixKey,
            BigDecimal amount,
            CurrencyType currency,
            Instant createdAt,
            Instant processedAt,
            PixTransactionStatus status) {
        this.id = id;
        this.endToEndId = endToEndId;
        this.idempotencyKey = idempotencyKey;
        this.fromWalletId = fromWalletId;
        this.toWalletId = toWalletId;
        this.toPixKey = toPixKey;
        this.amount = amount;
        this.currency = currency;
        this.createdAt = createdAt;
        this.processedAt = processedAt;
        this.status = status;
    }

    public PixTransaction(
            Long fromWalletId,
            Long toWalletId,
            UUID idempotencyKey,
            String toPixKey,
            BigDecimal amount,
            CurrencyType currency) {
        this.endToEndId = PixUtil.generateEndToEnd();
        this.idempotencyKey = idempotencyKey;
        this.fromWalletId = fromWalletId;
        this.toWalletId = toWalletId;
        this.toPixKey = toPixKey;
        this.amount = amount;
        this.currency = currency;
        this.createdAt = Instant.now();
        this.status = PixTransactionStatus.PENDING;
    }

    public PixTransaction confirm() {
        if (!getStatus().equals(PixTransactionStatus.PENDING)) {
            throw new InvalidPixTransactionStatusException("The status needs to be PENDING.");
        }

        this.status = PixTransactionStatus.CONFIRMED;
        this.processedAt = Instant.now();

        return this;
    }

    public PixTransaction reject() {
        if (!getStatus().equals(PixTransactionStatus.PENDING)) {
            throw new InvalidPixTransactionStatusException("The status needs to be PENDING.");
        }

        this.status = PixTransactionStatus.REJECTED;
        this.processedAt = Instant.now();

        return this;
    }

    public Long getId() {
        return id;
    }

    public String getEndToEndId() {
        return endToEndId;
    }

    public UUID getIdempotencyKey() {
        return idempotencyKey;
    }

    public Long getFromWalletId() {
        return fromWalletId;
    }

    public Long getToWalletId() {
        return toWalletId;
    }

    public String getToPixKey() {
        return toPixKey;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public CurrencyType getCurrency() {
        return currency;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getProcessedAt() {
        return processedAt;
    }

    public PixTransactionStatus getStatus() {
        return status;
    }
}
