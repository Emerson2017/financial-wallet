package com.finaya.wallete.infrastructure.persistence.repository;

import com.finaya.wallete.infrastructure.persistence.entity.WalletEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletJpaRepository extends JpaRepository<WalletEntity, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT w FROM WalletEntity w WHERE w.id = :id")
    Optional<WalletEntity> findByIdWithLock(Long id);
}
