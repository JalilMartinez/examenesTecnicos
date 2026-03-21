package com.api.dataprocessor.repository;

import com.api.dataprocessor.model.entity.TransactionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    boolean existsByReferencia(String referencia);
    Page<TransactionEntity> findAll(Pageable pageable);

}
