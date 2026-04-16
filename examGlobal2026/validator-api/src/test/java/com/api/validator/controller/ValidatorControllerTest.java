package com.api.validator.controller;

import com.api.validator.model.dto.TransactionRequestDto;
import com.api.validator.model.dto.TransactionResponseDto;
import com.api.validator.service.HashService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ValidatorControllerTest {

    @Autowired
    private ValidatorController validatorController;
    @Autowired
    private HashService hashService;

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
        String transactionResponseMSJ = (String) response.getBody();
        assertEquals("Cuerpo valido",transactionResponseMSJ,"El estatus no coincide");
    }
}
