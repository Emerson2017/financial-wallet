package com.finaya.wallete.domain.model;

import com.finaya.wallete.domain.enums.PixKeyType;

public class PixKey {

    private Long id;

    private final Wallet wallet;

    private final PixKeyType pixKeyType;

    private final String key;

    public PixKey(Wallet wallet, PixKeyType pixKeyType, String key) {
        this.wallet = wallet;
        this.pixKeyType = pixKeyType;
        this.key = key;
    }

    public Long getId() {
        return id;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public PixKeyType getPixKeyType() {
        return pixKeyType;
    }

    public String getKey() {
        return key;
    }

}
