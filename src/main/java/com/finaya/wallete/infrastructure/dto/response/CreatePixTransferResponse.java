package com.finaya.wallete.infrastructure.dto.response;

import com.finaya.wallete.domain.enums.PixTransactionStatus;
import com.finaya.wallete.domain.model.PixTransaction;

public record CreatePixTransferResponse(String endToEndId, PixTransactionStatus status) {

    public CreatePixTransferResponse(PixTransaction transaction) {
        this(transaction.getEndToEndId(), transaction.getStatus());
    }
}
