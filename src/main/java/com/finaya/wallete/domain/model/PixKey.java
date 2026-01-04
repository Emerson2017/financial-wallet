package com.finaya.wallete.domain.model;

import com.finaya.wallete.domain.enums.PixKeyType;

public class PixKey {

    private Long id;

    private final Wallet walletId;

    private final PixKeyType pixKeyType;

    private final String key;

    public PixKey(Wallet walletId, PixKeyType pixKeyType, String key) {
        this.walletId = walletId;
        this.pixKeyType = pixKeyType;
        this.key = key;
    }

    public Long getId() {
        return id;
    }

    public Wallet getWalletId() {
        return walletId;
    }

    public PixKeyType getPixKeyType() {
        return pixKeyType;
    }

    public String getKey() {
        return key;
    }

}
