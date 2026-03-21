package com.api.validator.handlerException;


import com.api.validator.model.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGlobalException(Exception exception) {

        exception.printStackTrace();
        ErrorResponseDto error = new ErrorResponseDto(
                "Ocurrió un error inesperado en el servidor", // Mensaje genérico por seguridad
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,Object>> handleGlobalException(MethodArgumentNotValidException exception) {
        Map<String,String> errors= new HashMap<>();

        exception.getBindingResult().getFieldErrors().forEach(error->
                errors.put(error.getField(),error.getDefaultMessage())
        );
        exception.printStackTrace();
        Map<String,Object> response = new HashMap<>();
        response.put("mensaje","Datos no validos");
        response.put("errores",errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
    @ExceptionHandler(AlgorithShaException.class)
    public ResponseEntity<ErrorResponseDto> handleAlgorithShaException(AlgorithShaException exception) {

        exception.printStackTrace();
        ErrorResponseDto error = new ErrorResponseDto(
                "Ocurrió un error inesperado en el servidor", // Mensaje genérico por seguridad
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
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

}
