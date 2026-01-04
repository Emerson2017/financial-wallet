package com.finaya.wallete.application.port.out;

import com.finaya.wallete.domain.model.Wallet;

import java.util.Optional;

public interface WalletRepositoryPort {
    Optional<Wallet> findById(Long id);
    Wallet save(Wallet wallet);
}
