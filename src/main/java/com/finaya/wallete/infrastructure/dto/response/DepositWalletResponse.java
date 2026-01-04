package com.finaya.wallete.infrastructure.dto.response;

import com.finaya.wallete.domain.enums.CurrencyType;

import java.math.BigDecimal;
import java.util.UUID;

public record DepositWalletResponse(UUID walletCode,
                                    UUID eventId,
                                    BigDecimal depositedAmount,
                                    CurrencyType currencyType) {
}
