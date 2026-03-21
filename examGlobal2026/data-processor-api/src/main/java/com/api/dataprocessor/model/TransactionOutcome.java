package com.api.dataprocessor.model;

import lombok.Data;

@Data
public class TransactionOutcome {
    private boolean correct;
    private StringBuilder message;

    public TransactionOutcome(){}
    public TransactionOutcome (boolean isCorrect){
        this.setCorrect(isCorrect);
        this.message = new StringBuilder();
    }

    public void setMessage(String messaje){
        this.message.append(messaje);
    }
    public String getMessage(){
        return this.message.toString();
    }
}
