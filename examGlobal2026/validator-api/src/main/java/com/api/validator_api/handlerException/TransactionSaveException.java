package com.api.validator_api.handlerException;

public class TransactionSaveException extends RuntimeException{
    public TransactionSaveException(String message, Throwable e){
        super(message, e);
    }
}
