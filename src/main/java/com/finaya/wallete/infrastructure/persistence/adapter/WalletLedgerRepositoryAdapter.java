package com.finaya.wallete.infrastructure.persistence.adapter;

import com.finaya.wallete.application.port.out.WalletLedgerRepositoryPort;
import com.finaya.wallete.domain.model.WalletLedger;
import com.finaya.wallete.infrastructure.mapper.WalletLedgerMapper;
import com.finaya.wallete.infrastructure.persistence.entity.WalletLedgerEntity;
import com.finaya.wallete.infrastructure.persistence.repository.WalletLedgerJpaRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;

@Component
public class WalletLedgerRepositoryAdapter implements WalletLedgerRepositoryPort {

    private final WalletLedgerMapper mapper;
    private final WalletLedgerJpaRepository repository;

    public WalletLedgerRepositoryAdapter(WalletLedgerJpaRepository repository, WalletLedgerMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Optional<WalletLedger> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public WalletLedger save(WalletLedger walletLedger) {
        WalletLedgerEntity walletLedgerEntity = mapper.toEntity(walletLedger);
        return mapper.toDomain(repository.save(walletLedgerEntity));
    }

    public BigDecimal calculateBalance(Long walletId, Instant at) {
        return repository.calculateBalance(walletId, at);
    }
}
