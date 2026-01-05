package com.finaya.wallete.infrastructure.persistence.entity;

import com.finaya.wallete.domain.enums.CurrencyType;
import com.finaya.wallete.domain.enums.PixTransactionStatus;
import com.finaya.wallete.domain.model.Wallet;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.type.PostgreSQLEnumJdbcType;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "pix_transaction")
public class PixTransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "end_to_end_id")
    private String endToEndId;

    @Column(name = "idempotency_key")
    private UUID idempotencyKey;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_wallet_id")
    private WalletEntity fromWallet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_wallet_id")
    private WalletEntity toWallet;

    @Column(name = "to_pix_key")
    private String toPixKey;

    @Column
    private BigDecimal amount;

    @Column
    @Enumerated(EnumType.STRING)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    private CurrencyType currency;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "processed_at")
    private Instant processedAt;

    @Column
    @Enumerated(EnumType.STRING)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    private PixTransactionStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEndToEndId() {
        return endToEndId;
    }

    public void setEndToEndId(String endToEndId) {
        this.endToEndId = endToEndId;
    }

    public UUID getIdempotencyKey() {
        return idempotencyKey;
    }

    public void setIdempotencyKey(UUID idempotencyKey) {
        this.idempotencyKey = idempotencyKey;
    }

    public WalletEntity getFromWallet() {
        return fromWallet;
    }

    public void setFromWallet(WalletEntity fromWallet) {
        this.fromWallet = fromWallet;
    }

    public WalletEntity getToWallet() {
        return toWallet;
    }

    public void setToWallet(WalletEntity toWallet) {
        this.toWallet = toWallet;
    }

    public String getToPixKey() {
        return toPixKey;
    }

    public void setToPixKey(String toPixKey) {
        this.toPixKey = toPixKey;
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

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getProcessedAt() {
        return processedAt;
    }

    public void setProcessedAt(Instant processedAt) {
        this.processedAt = processedAt;
    }

    public PixTransactionStatus getStatus() {
        return status;
    }

    public void setStatus(PixTransactionStatus status) {
        this.status = status;
    }
}
