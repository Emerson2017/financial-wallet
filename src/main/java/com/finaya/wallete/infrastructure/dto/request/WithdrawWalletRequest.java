package com.finaya.wallete.infrastructure.dto.request;

import java.math.BigDecimal;

public record WithdrawWalletRequest(BigDecimal amount) {
}
