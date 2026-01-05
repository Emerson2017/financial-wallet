package com.finaya.wallete.application.usecase.pix;

import com.finaya.wallete.application.port.out.PixTransactionRepositoryPort;
import com.finaya.wallete.application.service.PixTransactionService;
import com.finaya.wallete.domain.model.PixTransaction;
import com.finaya.wallete.infrastructure.dto.request.WebhookPixRequest;
import org.springframework.transaction.annotation.Transactional;

public class ProcessPixWebHookUseCase {

    private final PixTransactionRepositoryPort pixTransactionRepositoryPort;
    private final PixTransactionService pixTransactionService;

    public ProcessPixWebHookUseCase(PixTransactionRepositoryPort pixTransactionRepositoryPort, PixTransactionService pixTransactionService) {
        this.pixTransactionRepositoryPort = pixTransactionRepositoryPort;
        this.pixTransactionService = pixTransactionService;
    }

    @Transactional
    public void execute(WebhookPixRequest request) {

        PixTransaction pixTransaction = pixTransactionRepositoryPort
                .findByEndToEndId(request.endToEndId())
                .orElseThrow(() -> new RuntimeException("Pix Transaction not found"));

        switch (request.eventType()) {
            case CONFIRMED -> pixTransactionService.confirm(pixTransaction);
            case REJECTED    -> pixTransactionService.reject(pixTransaction);
            default        -> throw new IllegalArgumentException("Unsupported event type: " + request.eventType());
        }
    }
}
