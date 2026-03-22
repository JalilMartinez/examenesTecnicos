package com.api.dataprocessor.handlerException;

import com.api.dataprocessor.model.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleNotFound(ResourceNotFoundException exception){
        exception.printStackTrace();
        ErrorResponseDto error = new ErrorResponseDto(
                exception.getMessage(),
                HttpStatus.NOT_FOUND.value()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

    }
    @ExceptionHandler(TransactionSaveException.class)
    public ResponseEntity<ErrorResponseDto> handleSaveError(TransactionSaveException exception){
        exception.printStackTrace();
        ErrorResponseDto error = new ErrorResponseDto(
                exception.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponseDto> handleMisingServletException(MissingServletRequestParameterException exception) {

        exception.printStackTrace();
        ErrorResponseDto error = new ErrorResponseDto(
                String.format("El parametro '%s' faltante en la petición",exception.getParameterName()), // Mensaje genérico por seguridad
                HttpStatus.BAD_REQUEST.value()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGlobalException(Exception exception) {

        exception.printStackTrace();
        ErrorResponseDto error = new ErrorResponseDto(
                "Ocurrió un error inesperado en el servidor", // Mensaje genérico por seguridad
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

}
