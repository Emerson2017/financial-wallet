package com.finaya.wallete.infrastructure.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record DepositWalletRequest(
        BigDecimal amount
    ) {
}
