package com.api.data_processor_api.repository;

import com.api.data_processor_api.model.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    boolean existsByReferencia(String referencia);

}
