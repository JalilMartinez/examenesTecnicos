package com.api.dataprocessor.handlerException;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message, Throwable e){
        super(message, e);
    }
    public ResourceNotFoundException(String message){
        super(message);
    }
}
