package com.api.dataprocessor.model;

import com.api.dataprocessor.model.dto.TransactionResponseDto;
import lombok.Data;

@Data
public class TransactionOutcomeResponse extends TransactionOutcome {
    private TransactionResponseDto transactionResponseDto;

    public TransactionOutcomeResponse (boolean correct){
        super(correct);
    }
}
