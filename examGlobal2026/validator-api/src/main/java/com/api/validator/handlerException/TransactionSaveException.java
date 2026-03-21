package com.api.validator.handlerException;

public class TransactionSaveException extends RuntimeException{
    public TransactionSaveException(String message, Throwable e){
        super(message, e);
    }
}
