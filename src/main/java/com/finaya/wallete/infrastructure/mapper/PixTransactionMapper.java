package com.finaya.wallete.infrastructure.mapper;

import com.finaya.wallete.domain.model.PixTransaction;
import com.finaya.wallete.infrastructure.persistence.entity.PixTransactionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ObjectFactory;

@Mapper(componentModel = "spring")
public interface PixTransactionMapper {

    PixTransactionEntity toEntity(PixTransaction pixTransaction);

    PixTransaction toDomain(PixTransactionEntity pixTransactionEntity);

    @ObjectFactory
    default PixTransaction createWallet(PixTransactionEntity entity) {
        return new PixTransaction(
                entity.getId(),
                entity.getEndToEndId(),
                entity.getIdempotencyKey(),
                entity.getFromWalletId(),
                entity.getToWalletId(),
                entity.getToPixKey(),
                entity.getAmount(),
                entity.getCurrency(),
                entity.getCreatedAt(),
                entity.getProcessedAt(),
                entity.getStatus()
        );
    }
}
