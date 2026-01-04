package com.finaya.wallete.application.usecase.wallet;

import com.finaya.wallete.application.port.out.WalletRepositoryPort;
import com.finaya.wallete.domain.model.Wallet;

public class CreateWalletUseCase {

    private final WalletRepositoryPort repository;

    public CreateWalletUseCase(WalletRepositoryPort repository) {
        this.repository = repository;
    }

    public Wallet execute(Long userId) {
        Wallet wallet = new Wallet(userId);
        return repository.save(wallet);
    }

}
