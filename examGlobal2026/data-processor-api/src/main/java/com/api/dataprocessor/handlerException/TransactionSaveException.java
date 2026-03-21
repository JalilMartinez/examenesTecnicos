package com.api.dataprocessor.handlerException;

public class TransactionSaveException extends RuntimeException{
    public TransactionSaveException(String message, Throwable e){
        super(message, e);
    }
}
