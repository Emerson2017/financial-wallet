package com.finaya.wallete.infrastructure.mapper;

import com.finaya.wallete.domain.model.PixTransaction;
import com.finaya.wallete.infrastructure.persistence.entity.PixTransactionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        uses = WalletMapper.class)
public interface PixTransactionMapper {

    PixTransactionEntity toEntity(PixTransaction pixTransaction);

}
