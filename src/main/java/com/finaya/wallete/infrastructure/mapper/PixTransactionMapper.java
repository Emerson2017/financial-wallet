package com.finaya.wallete.infrastructure.mapper;

import com.finaya.wallete.domain.model.PixTransaction;
import com.finaya.wallete.infrastructure.persistence.entity.PixTransactionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ObjectFactory;

@Mapper(componentModel = "spring",
        uses = WalletMapper.class)
public interface PixTransactionMapper {

    PixTransactionEntity toEntity(PixTransaction pixTransaction);

//    PixTransaction toDomain(PixTransactionEntity pixTransactionEntity);
//
//    @ObjectFactory
//    default PixTransaction createWallet(PixTransactionEntity entity,
//                                        WalletMapper walletMapper) {
//        return new PixTransaction(
//                entity.getId(),
//                entity.getEndToEndId(),
//                entity.getIdempotencyKey(),
//                walletMapper.toDomain(entity.getFromWallet()),
//                walletMapper.toDomain(entity.getToWallet()),
//                entity.getToPixKey(),
//                entity.getAmount(),
//                entity.getCurrency(),
//                entity.getCreatedAt(),
//                entity.getProcessedAt(),
//                entity.getStatus()
//        );
//    }
}
