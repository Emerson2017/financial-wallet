package com.finaya.wallete.infrastructure.persistence.repository;

import com.finaya.wallete.infrastructure.persistence.entity.PixTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PixTransactionJpaRepository extends JpaRepository<PixTransactionEntity, Long> {
    Optional<PixTransactionEntity> findByIdempotencyKey(UUID idempotencyKey);
    Optional<PixTransactionEntity> findByEndToEndId(String endToEndId);
}
