package com.api.data_processor_api.HandlerException;

import com.api.data_processor_api.Entities.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException exception){
        ErrorResponse error = new ErrorResponse(
                exception.getMessage(),
                HttpStatus.NOT_FOUND.value()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

    }

}
