package com.finaya.wallete.infrastructure.controller;

import com.finaya.wallete.application.usecase.pix.CreatePixTransferUseCase;
import com.finaya.wallete.application.usecase.pix.ProcessPixWebHookUseCase;
import com.finaya.wallete.infrastructure.dto.request.CreatePixTransferRequest;
import com.finaya.wallete.infrastructure.dto.request.WebhookPixRequest;
import com.finaya.wallete.infrastructure.dto.response.CreatePixTransferResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/pix")
public class PixController {

    private final CreatePixTransferUseCase createPixTransferUseCase;
    private final ProcessPixWebHookUseCase processPixWebHookUseCase;

    public PixController(CreatePixTransferUseCase createPixTransferUseCase,
                         ProcessPixWebHookUseCase processPixWebHookUseCase) {
        this.createPixTransferUseCase = createPixTransferUseCase;
        this.processPixWebHookUseCase = processPixWebHookUseCase;
    }

    @PostMapping("/transfers")
    public CreatePixTransferResponse createPixTransfer(@RequestHeader("Idempotency-Key") UUID idempotencyKey,
                                                       @RequestBody @Valid CreatePixTransferRequest createPixTransferRequest) throws InterruptedException {
        return createPixTransferUseCase.execute(createPixTransferRequest, idempotencyKey);
    }

    @PostMapping("/webhook")
    public ResponseEntity<?> webhookPix(@RequestBody @Valid WebhookPixRequest request) {
        processPixWebHookUseCase.execute(request);
        return ResponseEntity.ok().build();
    }
}
