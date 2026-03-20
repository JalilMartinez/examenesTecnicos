package com.api.data_processor_api.model.dto;

import lombok.Data;

@Data
public class TransactionOutcomeResponse extends TransactionOutcome{
    private TransactionResponse transactionResponse;

    public TransactionOutcomeResponse (boolean correct){
        super(correct);
    }
}
