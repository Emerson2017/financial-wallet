package com.finaya.wallete.domain.repository;

import com.finaya.wallete.infrastructure.persistence.entity.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalleteRepository extends JpaRepository<WalletEntity, Long> {
}
