package com.finaya.wallete.infrastructure.mapper;

import com.finaya.wallete.domain.model.Wallet;
import com.finaya.wallete.infrastructure.persistence.entity.WalletEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ObjectFactory;

@Mapper(componentModel = "spring")
public interface WalletMapper {

    WalletEntity toEntity(Wallet wallet);

    Wallet toDomain(WalletEntity entity);

    @ObjectFactory
    default Wallet createWallet(WalletEntity entity) {
        return new Wallet(
                entity.getId(),
                entity.getCode(),
                entity.getUserId(),
                entity.getCurrency()
        );
    }
}
