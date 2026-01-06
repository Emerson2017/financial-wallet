package com.finaya.wallete.infrastructure.dto.response;

import com.finaya.wallete.domain.enums.CurrencyType;

import java.math.BigDecimal;
import java.util.UUID;

public record WithdrawWalletResponse(UUID walletCode,
                                     UUID eventId,
                                     BigDecimal withdrawAmount,
                                     CurrencyType currency) {
}
