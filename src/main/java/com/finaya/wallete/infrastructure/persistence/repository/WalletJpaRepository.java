package com.finaya.wallete.infrastructure.persistence.repository;

import com.finaya.wallete.infrastructure.persistence.entity.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletJpaRepository extends JpaRepository<WalletEntity, Long> {
}
