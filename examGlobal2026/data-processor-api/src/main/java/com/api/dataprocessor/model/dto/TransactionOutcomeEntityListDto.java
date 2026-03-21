package com.api.dataprocessor.model.dto;

import com.api.dataprocessor.model.TransactionOutcome;
import com.api.dataprocessor.model.entity.TransactionEntity;
import lombok.Data;

import java.util.List;

@Data
public class TransactionOutcomeEntityListDto extends TransactionOutcome {
    private List<TransactionEntity> entityList;
    private long totalElements;
    private int currentPage;
    private int totalPages;
    public TransactionOutcomeEntityListDto(boolean correct){
        super(correct);
    }
}
