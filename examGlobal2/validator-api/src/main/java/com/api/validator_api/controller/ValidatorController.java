package com.api.validator_api.controller;

import com.api.validator_api.entities.TransactionRequest;
import com.api.validator_api.entities.TransactionResponse;
import com.api.validator_api.service.ValidationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
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
