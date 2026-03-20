package com.api.data_processor_api.model.dto;

import com.api.data_processor_api.model.entity.TransactionEntity;
import lombok.Data;

import java.util.List;

@Data
public class TransactionOutcomeEntityList extends TransactionOutcome {
    private List<TransactionEntity> entityList;

    public TransactionOutcomeEntityList (boolean correct){
        super(correct);
    }
}
