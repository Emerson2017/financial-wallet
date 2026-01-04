package com.finaya.wallete.infrastructure.dto.response;

import com.finaya.wallete.domain.enums.PixKeyType;

import java.util.UUID;

public record CreatePixKeyResponse (
        UUID walletCode,
        PixKeyType pixKeyType,
        String key
){
}
