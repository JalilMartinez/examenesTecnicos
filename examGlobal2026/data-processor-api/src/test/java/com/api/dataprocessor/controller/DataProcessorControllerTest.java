package com.api.dataprocessor.controller;

import com.api.dataprocessor.model.dto.TransactionRequestDto;
import com.api.dataprocessor.model.dto.TransactionResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DataProcessorControllerTest {
    @Autowired
    DataProcessorController dataProcessorController;

    @Test
    public void processTransactionFailTest(){
        TransactionRequestDto transactionRequestDto = new TransactionRequestDto();
        ResponseEntity<?> transaction = dataProcessorController.processTransaction(transactionRequestDto);
        assertEquals(HttpStatus.BAD_REQUEST,transaction.getStatusCode(),"Estatus de la transaccion incorrecto");
        Map<String,String> error = (Map<String, String>) transaction.getBody();
        assertEquals("Cuerpo de transacción vacio",error.get("message"),"Mensaje de error no coincide");
    }

    @Test
    public void processTransactionTest(){
        TransactionRequestDto transactionRequestDto = new TransactionRequestDto();
        transactionRequestDto.setCliente("Jalil");
        transactionRequestDto.setFirma("fsdfe");
        transactionRequestDto.setImporte(new BigDecimal("50.01"));
        transactionRequestDto.setOperacion("venta");
        ResponseEntity<?> transaction = dataProcessorController.processTransaction(transactionRequestDto);
        assertEquals(HttpStatus.OK,transaction.getStatusCode(),"Estatus de la peticion no coincide");
        TransactionResponseDto transactionResponseDto = (TransactionResponseDto) transaction.getBody();
        assertEquals("venta", transactionResponseDto.getOperacion(),"La operacion no coincide");
    }
}
