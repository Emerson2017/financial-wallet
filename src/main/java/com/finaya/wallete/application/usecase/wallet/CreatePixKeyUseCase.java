package com.finaya.wallete.application.usecase.wallet;

import com.finaya.wallete.application.port.out.PixKeyRepositoryPort;
import com.finaya.wallete.application.port.out.WalletRepositoryPort;
import com.finaya.wallete.domain.enums.PixKeyType;
import com.finaya.wallete.domain.exception.EntityNotFoundException;
import com.finaya.wallete.domain.model.PixKey;
import com.finaya.wallete.domain.model.Wallet;
import com.finaya.wallete.infrastructure.dto.request.CreatePixKeyRequest;
import com.finaya.wallete.infrastructure.dto.response.CreatePixKeyResponse;
import com.finaya.wallete.infrastructure.mapper.PixKeyMapper;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public CreatePixKeyResponse execute(Long walletId, CreatePixKeyRequest request) {
        Wallet wallet = walletRepositoryPort.findByIdWithLock(walletId)
                .orElseThrow(() -> new EntityNotFoundException("Wallet Not Found"));

        PixKey pixKey = (request.pixKeyType() == PixKeyType.EVP)
                ? new PixKey(wallet)
                : new PixKey(wallet, request.pixKeyType(), request.key());

        return mapper.toResponse(pixKeyRepositoryPort.save(pixKey));
    }
}
