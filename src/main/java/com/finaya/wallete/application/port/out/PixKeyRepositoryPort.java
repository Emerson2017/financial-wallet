package com.finaya.wallete.application.port.out;

import com.finaya.wallete.domain.model.PixKey;

import java.util.Optional;

public interface PixKeyRepositoryPort {
    Optional<PixKey> findById(Long id);
    Optional<PixKey> findByKey(String key);
    PixKey save(PixKey pixKey);
}
