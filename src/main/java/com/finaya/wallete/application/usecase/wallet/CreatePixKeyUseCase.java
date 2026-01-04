package com.finaya.wallete.application.usecase.wallet;

import com.finaya.wallete.application.port.out.PixKeyRepositoryPort;
import com.finaya.wallete.application.port.out.WalletRepositoryPort;
import com.finaya.wallete.domain.model.PixKey;
import com.finaya.wallete.domain.model.Wallet;
import com.finaya.wallete.infrastructure.dto.request.CreatePixKeyRequest;
import com.finaya.wallete.infrastructure.dto.response.CreatePixKeyResponse;
import com.finaya.wallete.infrastructure.mapper.PixKeyMapper;

import java.util.Optional;

public class CreatePixKeyUseCase {

    private final PixKeyRepositoryPort pixKeyRepositoryPort;

    private final WalletRepositoryPort walletRepositoryPort;

    private final PixKeyMapper mapper;

    public CreatePixKeyUseCase(PixKeyRepositoryPort pixKeyRepositoryPort, WalletRepositoryPort walletRepositoryPort, PixKeyMapper mapper) {
        this.pixKeyRepositoryPort = pixKeyRepositoryPort;
        this.walletRepositoryPort = walletRepositoryPort;
        this.mapper = mapper;
    }

    public CreatePixKeyResponse execute(Long idWallet, CreatePixKeyRequest request) {
        Optional<Wallet> wallet = walletRepositoryPort.findById(idWallet);

        if (wallet.isEmpty()) {
            throw new RuntimeException("Wallet Not Found.");
        }

        PixKey pixKey = new PixKey(wallet.get(), request.pixKeyType(), request.key());
        return mapper.toResponse(pixKeyRepositoryPort.save(pixKey));
    }
}
