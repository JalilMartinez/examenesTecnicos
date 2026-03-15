package com.api.validator_api.controller;

import com.api.validator_api.entities.TransactionRequest;
import com.api.validator_api.entities.TransactionResponse;
import com.api.validator_api.service.ValidationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/save-transaction-api")
public class ValidatorController {

    @Autowired
    ValidationService validationService;

    @PostMapping("")
    private ResponseEntity<TransactionResponse> doTransaction(@Valid @RequestBody TransactionRequest transactionRequest) throws NoSuchAlgorithmException {
        TransactionResponse transactionResponse = new TransactionResponse();
        validationService.processTransaction(transactionRequest,transactionResponse);
        return ResponseEntity.ok(transactionResponse);
    }
}
