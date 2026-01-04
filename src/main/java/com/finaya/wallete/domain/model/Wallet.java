package com.finaya.wallete.domain.model;

import com.finaya.wallete.domain.enums.CurrencyType;
import com.finaya.wallete.domain.enums.WalletMovementType;

import java.math.BigDecimal;
import java.util.UUID;

public class Wallet {

    private Long id;
    private UUID code;
    private final Long userId;
    private final CurrencyType currency;

    public Wallet(Long userId) {
        this.code = UUID.randomUUID();
        this.userId = userId;
        this.currency = CurrencyType.BRL;
    }

    public Wallet(Long id, UUID code, Long userId, CurrencyType currency) {
        this.id = id;
        this.code = code;
        this.userId = userId;
        this.currency = currency;
    }

    public Long getId() {
        return id;
    }

    public UUID getCode() {
        return code;
    }

    public Long getUserId() {
        return userId;
    }

    public CurrencyType getCurrency() {
        return  currency;
    }

    public WalletLedger credit(BigDecimal amount, WalletMovementType movementType){
        if (amount.signum() <= 0) throw new RuntimeException("Deposit amount must be positive");
        return WalletLedger.credit(this, amount, movementType);
    }

    public WalletLedger debit(BigDecimal amount, WalletMovementType movementType){
        if (amount.signum() <= 0) throw new RuntimeException("Withdraw amount must be positive");
        return WalletLedger.debit(this, amount, movementType);
    }
}
