package com.finaya.wallete.domain.model;

import com.finaya.wallete.domain.enums.PixKeyType;
import com.finaya.wallete.domain.util.PixUtil;

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

    public PixKey(Wallet wallet) {
        this.wallet = wallet;
        this.pixKeyType = PixKeyType.EVP;
        this.key = PixUtil.generateEVPKey();
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
