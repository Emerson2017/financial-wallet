package com.finaya.wallete.infrastructure.dto.response;

import com.finaya.wallete.domain.enums.CurrencyType;

import java.math.BigDecimal;

public record BalanceResponse(BigDecimal balance, CurrencyType currency) {
}
