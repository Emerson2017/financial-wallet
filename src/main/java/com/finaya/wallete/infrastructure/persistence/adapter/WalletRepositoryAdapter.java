package com.finaya.wallete.infrastructure.persistence.adapter;

import com.finaya.wallete.application.port.out.WalletRepositoryPort;
import com.finaya.wallete.domain.model.Wallet;
import com.finaya.wallete.infrastructure.mapper.WalletMapper;
import com.finaya.wallete.infrastructure.persistence.entity.WalletEntity;
import com.finaya.wallete.infrastructure.persistence.repository.WalletJpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class WalletRepositoryAdapter implements WalletRepositoryPort {

    private final WalletJpaRepository repository;

    private final WalletMapper mapper;

    public WalletRepositoryAdapter(WalletJpaRepository walletJpaRepository, WalletMapper walletMapper) {
        this.repository = walletJpaRepository;
        this.mapper = walletMapper;
    }

    @Override
    public Optional<Wallet> findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<Wallet> findByIdWithLock(Long id) {
        return repository.findByIdWithLock(id)
                .map(mapper::toDomain);
    }

    @Override
    public Wallet save(Wallet wallet) {
        WalletEntity walletEntity = mapper.toEntity(wallet);
        repository.save(walletEntity);
        return mapper.toDomain(walletEntity);
    }
}
