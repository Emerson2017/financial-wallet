package com.finaya.wallete.infrastructure.persistence.adapter;

import com.finaya.wallete.application.port.out.PixKeyRepositoryPort;
import com.finaya.wallete.domain.model.PixKey;
import com.finaya.wallete.infrastructure.mapper.PixKeyMapper;
import com.finaya.wallete.infrastructure.persistence.entity.PixKeyEntity;
import com.finaya.wallete.infrastructure.persistence.repository.PixKeyJpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PixKeyRepositoryAdapter implements PixKeyRepositoryPort {

    private final PixKeyJpaRepository repository;

    private final PixKeyMapper mapper;

    PixKeyRepositoryAdapter(PixKeyJpaRepository pixKeyJpaRepository,  PixKeyMapper pixKeyMapper) {
        this.repository = pixKeyJpaRepository;
        this.mapper = pixKeyMapper;
    }

    @Override
    public Optional<PixKey> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<PixKey> findByKey(String key) {
        return repository.findByKey(key)
                .map(mapper::toDomain);
    }

    @Override
    public PixKey save(PixKey pixKey) {
        PixKeyEntity pixKeyEntity = mapper.toEntity(pixKey);
        return mapper.toDomain(repository.save(pixKeyEntity));
    }
}
