package com.api.validator_api.model.dto;

import lombok.Data;

@Data
public class TransactionOutcome {
    private boolean correct;
    private TransactionResponse transactionResponse;
    private StringBuilder message;

    public TransactionOutcome (boolean isCorrect){
        this.setCorrect(isCorrect);
        this.message = new StringBuilder();
        this.transactionResponse = new TransactionResponse();
    }
    public void setMessage(String messaje){
        this.message.append(messaje);
    }
    public String getMessage(){
        return this.message.toString();
    }
}
