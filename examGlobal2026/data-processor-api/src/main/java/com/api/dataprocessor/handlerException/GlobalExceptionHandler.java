package com.api.dataprocessor.handlerException;

import com.api.dataprocessor.model.dto.ErrorResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.HandlerExceptionResolver;


@RestControllerAdvice
public class GlobalExceptionHandler {

    public static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleNotFound(ResourceNotFoundException exception){
        logger.error(" Recurso no encontrado: ", exception);
        ErrorResponseDto error = new ErrorResponseDto(
                exception.getMessage(),
                HttpStatus.NOT_FOUND.value()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

    }
    @ExceptionHandler(TransactionSaveException.class)
    public ResponseEntity<ErrorResponseDto> handleSaveError(TransactionSaveException exception){
        logger.error(" Error ala guardar transaccion: ", exception);
        ErrorResponseDto error = new ErrorResponseDto(
                exception.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponseDto> handleMisingServletException(MissingServletRequestParameterException exception) {
        logger.error("Parametro faltante : ", exception);
        ErrorResponseDto error = new ErrorResponseDto(
                String.format("El parametro '%s' faltante en la petición",exception.getParameterName()), // Mensaje genérico por seguridad
                HttpStatus.BAD_REQUEST.value()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<ErrorResponseDto> handleAuthenticationException(AuthException exception) {
        logger.error("Error al realizar la autenticacion : ", exception);
        ErrorResponseDto error = new ErrorResponseDto(
                String.format("Error al realizar la autenticacion : %s",exception.getMessage()), // Mensaje genérico por seguridad
                HttpStatus.BAD_REQUEST.value()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(JWTException.class)
    public ResponseEntity<ErrorResponseDto> JWTException(JWTException exception) {
        logger.error("Error al realizar la autenticacion del token: ", exception);
        ErrorResponseDto error = new ErrorResponseDto(
                exception.getMessage(), // Mensaje genérico por seguridad
                HttpStatus.BAD_REQUEST.value()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGlobalException(Exception exception) {
        logger.error(" Error inesperado: ", exception);
        ErrorResponseDto error = new ErrorResponseDto(
                "Ocurrió un error inesperado en el servidor", // Mensaje genérico por seguridad
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

}
