package com.api.validator.model;

import com.api.validator.model.dto.TransactionResponseDto;
import lombok.Data;

@Data
public class TransactionOutcome {
    private boolean correct;
    private TransactionResponseDto transactionResponse;
    private StringBuilder message;

    public TransactionOutcome (boolean isCorrect){
        this.setCorrect(isCorrect);
        this.message = new StringBuilder();
        this.transactionResponse = new TransactionResponseDto();
    }
    public void setMessage(String messaje){
        this.message.append(messaje);
    }
    public String getMessage(){
        return this.message.toString();
    }
}
