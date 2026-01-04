package com.finaya.wallete.application.port.out;

import com.finaya.wallete.domain.model.PixTransaction;

import java.util.Optional;
import java.util.UUID;

public interface PixTransactionRepositoryPort {
    Optional<PixTransaction> findById(Long id);
    Optional<PixTransaction> findByIdempotencyKey(UUID idempotencyKey);
    Optional<PixTransaction> findByEndToEndId(String endToEndId);
    PixTransaction save(PixTransaction pixTransaction);
}
