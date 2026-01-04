package com.finaya.wallete.infrastructure.persistence.repository;

import com.finaya.wallete.infrastructure.persistence.entity.PixKeyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PixKeyJpaRepository extends JpaRepository<PixKeyEntity, Long> {
    Optional<PixKeyEntity> findByKey(String key);
}
