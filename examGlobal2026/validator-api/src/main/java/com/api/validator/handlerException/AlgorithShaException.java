package com.api.validator.handlerException;

public class AlgorithShaException extends RuntimeException{
    public AlgorithShaException(String message, Throwable e){
        super(message, e);
    }
}