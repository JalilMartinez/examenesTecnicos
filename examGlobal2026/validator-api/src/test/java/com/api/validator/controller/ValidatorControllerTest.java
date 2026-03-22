package com.api.validator.controller;

import com.api.validator.model.dto.TransactionRequestDto;
import com.api.validator.model.dto.TransactionResponseDto;
import com.api.validator.service.HashService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ValidatorControllerTest {

    private final ValidatorController validatorController;
    private final HashService hashService;

    public ValidatorControllerTest(ValidatorController validatorController, HashService hashService){
        this.validatorController = validatorController;
        this.hashService = hashService;
    }
    @Test
    public void doTransactionTest(){
        TransactionRequestDto transactionRequestDto = new TransactionRequestDto();
        transactionRequestDto.setCliente("Jalil");
        transactionRequestDto.setImporte(new BigDecimal("1234.5"));
        transactionRequestDto.setOperacion("Venta");
        String firma = hashService.generateSha("Venta|1234.5|Jalil");
        transactionRequestDto.setFirma(firma);
        ResponseEntity<?> response = validatorController.doTransaction(transactionRequestDto);
        assertEquals(HttpStatus.OK,response.getStatusCode(),"El estatus de la peticion es diferente");
        TransactionResponseDto transactionResponse = (TransactionResponseDto) response.getBody();
        assertEquals("Aprobada",transactionResponse.getEstatus(),"El estatus no coincide");
        assertEquals("Venta",transactionResponse.getOperacion(),"La operacion no coincide");
    }
}
