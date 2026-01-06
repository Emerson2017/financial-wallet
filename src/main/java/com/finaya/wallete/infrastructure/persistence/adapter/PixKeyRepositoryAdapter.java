package com.finaya.wallete.infrastructure.persistence.adapter;

import com.finaya.wallete.application.port.out.PixKeyRepositoryPort;
import com.finaya.wallete.domain.model.PixKey;
import com.finaya.wallete.infrastructure.mapper.PixKeyMapper;
import com.finaya.wallete.infrastructure.mapper.WalletMapper;
import com.finaya.wallete.infrastructure.persistence.entity.PixKeyEntity;
import com.finaya.wallete.infrastructure.persistence.repository.PixKeyJpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PixKeyRepositoryAdapter implements PixKeyRepositoryPort {

    private final PixKeyJpaRepository repository;
    private final WalletMapper walletMapper;
    private final PixKeyMapper mapper;

    public PixKeyRepositoryAdapter(PixKeyJpaRepository repository,
                                   WalletMapper walletMapper,
                                   PixKeyMapper mapper) {
        this.repository = repository;
        this.walletMapper = walletMapper;
        this.mapper = mapper;
    }

    @Override
    public Optional<PixKey> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<PixKey> findByKey(String key) {
        return repository.findByKey(key)
                .map(this::toDomain);
    }

    @Override
    public PixKey save(PixKey pixKey) {
        PixKeyEntity pixKeyEntity = mapper.toEntity(pixKey);
        return toDomain(repository.save(pixKeyEntity));
    }

    private PixKey toDomain(PixKeyEntity entity) {
        return new PixKey(
                walletMapper.toDomain(
                        entity.getWallet()),
                    entity.getPixKeyType(),
                    entity.getKey()
        );
    }
}
