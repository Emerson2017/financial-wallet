package com.finaya.wallete.application.usecase.wallet;

import com.finaya.wallete.application.port.out.WalletRepositoryPort;
import com.finaya.wallete.domain.model.Wallet;
import org.springframework.transaction.annotation.Transactional;

public class CreateWalletUseCase {

    private final WalletRepositoryPort repository;

    public CreateWalletUseCase(WalletRepositoryPort repository) {
        this.repository = repository;
    }

    @Transactional
    public Wallet execute(Long userId) {
        Wallet wallet = new Wallet(userId);
        return repository.save(wallet);
    }

}
