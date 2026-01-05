package com.finaya.wallete.infrastructure.persistence.adapter;

import com.finaya.wallete.application.port.out.PixTransactionRepositoryPort;
import com.finaya.wallete.domain.model.PixTransaction;
import com.finaya.wallete.infrastructure.mapper.PixTransactionMapper;
import com.finaya.wallete.infrastructure.mapper.WalletMapper;
import com.finaya.wallete.infrastructure.persistence.entity.PixTransactionEntity;
import com.finaya.wallete.infrastructure.persistence.repository.PixTransactionJpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class PixTransactionRepositoryAdapter implements PixTransactionRepositoryPort {

    private final PixTransactionJpaRepository repository;
    private final PixTransactionMapper pixTransactionMapper;
    private final WalletMapper walletMapper;

    public PixTransactionRepositoryAdapter(PixTransactionJpaRepository repository,
                                           PixTransactionMapper pixTransactionMapper,
                                           WalletMapper walletMapper) {
        this.pixTransactionMapper = pixTransactionMapper;
        this.walletMapper = walletMapper;
        this.repository = repository;
    }

    @Override
    public Optional<PixTransaction> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<PixTransaction> findByEndToEndId(String endToEndId) {
        return repository.findByEndToEndId(endToEndId)
                .map(this::toDomain);
    }

    @Override
    public Optional<PixTransaction> findByIdempotencyKey(UUID idempotencyKey) {
        return repository.findByIdempotencyKey(idempotencyKey)
                .map(this::toDomain);
    }

    @Override
    public PixTransaction save(PixTransaction pixTransaction) {
        PixTransactionEntity pixTransactionEntity = pixTransactionMapper.toEntity(pixTransaction);
        return this.toDomain(repository.save(pixTransactionEntity));
    }

    private PixTransaction toDomain(PixTransactionEntity e) {
        return new PixTransaction(
                e.getId(),
                e.getEndToEndId(),
                e.getIdempotencyKey(),
                walletMapper.toDomain(e.getFromWallet()),
                walletMapper.toDomain(e.getToWallet()),
                e.getToPixKey(),
                e.getAmount(),
                e.getCurrency(),
                e.getCreatedAt(),
                e.getProcessedAt(),
                e.getStatus()
        );
    }
}