package com.api.dataprocessor.handlerException;


public class AuthException extends RuntimeException {
    public AuthException(String message, Throwable reason){
        super(message, reason);
    }
}
