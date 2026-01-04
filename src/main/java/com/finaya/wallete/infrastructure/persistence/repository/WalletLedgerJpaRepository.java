package com.finaya.wallete.infrastructure.persistence.repository;

import com.finaya.wallete.infrastructure.persistence.entity.WalletLedgerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.Instant;

public interface WalletLedgerJpaRepository extends JpaRepository<WalletLedgerEntity, Long> {

    @Query("""
        select coalesce(
            sum(
                case
                    when l.balanceOperation = 'CREDIT' then l.amount
                    when l.balanceOperation = 'DEBIT'  then -l.amount
                    else 0
                end
            ),
            0
        )
        from WalletLedgerEntity l
        where l.walletId = :walletId
          and l.occurredAt <= :at
    """)
    BigDecimal calculateBalance(
            @Param("walletId") Long walletId,
            @Param("at") Instant at
    );
}
