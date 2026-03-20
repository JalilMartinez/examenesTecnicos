package com.api.validator_api.handlerException;


import com.api.validator_api.model.dto.ErrorResponse;
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
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception exception) {

        exception.printStackTrace();
        ErrorResponse error = new ErrorResponse(
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
    public ResponseEntity<ErrorResponse> handleAlgorithShaException(AlgorithShaException exception) {

        exception.printStackTrace();
        ErrorResponse error = new ErrorResponse(
                "Ocurrió un error inesperado en el servidor", // Mensaje genérico por seguridad
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(TransactionSaveException.class)
    public ResponseEntity<ErrorResponse> handleSaveError(TransactionSaveException exception){
        exception.printStackTrace();
        ErrorResponse error = new ErrorResponse(
                exception.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

}
