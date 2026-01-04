package com.finaya.wallete.infrastructure.mapper;

import com.finaya.wallete.domain.model.PixKey;
import com.finaya.wallete.infrastructure.dto.response.CreatePixKeyResponse;
import com.finaya.wallete.infrastructure.persistence.entity.PixKeyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        uses = WalletMapper.class)
public interface PixKeyMapper {

    PixKey toDomain(PixKeyEntity pixKeyEntity);

    PixKeyEntity toEntity(PixKey pixKey);

    @Mapping(target = "walletCode", source = "walletId.code")
    CreatePixKeyResponse toResponse(PixKey pixKey);
}
