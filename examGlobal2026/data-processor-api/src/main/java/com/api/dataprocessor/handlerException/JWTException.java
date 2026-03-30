package com.api.dataprocessor.handlerException;

public class JWTException extends RuntimeException{
    public JWTException(String message, Throwable cause){
        super(message, cause);
    }
}
