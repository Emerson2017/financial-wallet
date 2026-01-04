package com.finaya.wallete.infrastructure.dto.request;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreatePixTransferRequest(@NotNull Long fromWalletId,
                                       @NotNull String toPixKey,
                                       @NotNull BigDecimal amount) {
}
