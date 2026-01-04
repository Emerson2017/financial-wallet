package com.finaya.wallete.infrastructure.persistence.adapter;

import com.finaya.wallete.application.port.out.PixTransactionRepositoryPort;
import com.finaya.wallete.domain.model.PixTransaction;
import com.finaya.wallete.infrastructure.mapper.PixTransactionMapper;
import com.finaya.wallete.infrastructure.persistence.entity.PixTransactionEntity;
import com.finaya.wallete.infrastructure.persistence.repository.PixTransactionJpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class PixTransactionRepositoryAdapter implements PixTransactionRepositoryPort {

    private final PixTransactionJpaRepository repository;
    private final PixTransactionMapper mapper;

    public PixTransactionRepositoryAdapter(
            PixTransactionJpaRepository repository,
            PixTransactionMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Optional<PixTransaction> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<PixTransaction> findByEndToEndId(String endToEndId) {
        return repository.findByEndToEndId(endToEndId)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<PixTransaction> findByIdempotencyKey(UUID idempotencyKey) {
        return repository.findByIdempotencyKey(idempotencyKey)
                .map(mapper::toDomain);
    }

    @Override
    public PixTransaction save(PixTransaction pixTransaction) {
        PixTransactionEntity pixTransactionEntity = mapper.toEntity(pixTransaction);
        return mapper.toDomain(repository.save(pixTransactionEntity));
    }
}