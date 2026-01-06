package com.finaya.wallete.infrastructure.mapper;

import com.finaya.wallete.domain.model.PixKey;
import com.finaya.wallete.domain.model.Wallet;
import com.finaya.wallete.infrastructure.dto.response.CreatePixKeyResponse;
import com.finaya.wallete.infrastructure.persistence.entity.PixKeyEntity;
import com.finaya.wallete.infrastructure.persistence.entity.WalletEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ObjectFactory;

@Mapper(
        componentModel = "spring",
        uses = WalletMapper.class)
public interface PixKeyMapper {

    PixKeyEntity toEntity(PixKey pixKey);

    @Mapping(target = "walletCode", source = "wallet.code")
    CreatePixKeyResponse toResponse(PixKey pixKey);

}
