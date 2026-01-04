package com.finaya.wallete.infrastructure.mapper;

import com.finaya.wallete.domain.model.WalletLedger;
import com.finaya.wallete.infrastructure.persistence.entity.WalletLedgerEntity;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring")
public interface WalletLedgerMapper {

    WalletLedger toDomain(WalletLedgerEntity walletLedgerEntity);

    WalletLedgerEntity toEntity(WalletLedger walletLedger);
}
