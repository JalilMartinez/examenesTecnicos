package com.api.validator_api.handlerException;

public class AlgorithShaException extends RuntimeException{
    public AlgorithShaException(String message, Throwable e){
        super(message, e);
    }
}