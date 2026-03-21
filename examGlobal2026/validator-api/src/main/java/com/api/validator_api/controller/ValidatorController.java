package com.api.validator_api.controller;

import com.api.validator_api.model.dto.TransactionOutcome;
import com.api.validator_api.model.dto.TransactionRequest;
import com.api.validator_api.service.ValidationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/save-transaction-api")
public class ValidatorController {

    @Autowired
    ValidationService validationService;

    @PostMapping("")
    public ResponseEntity<?> doTransaction(@Valid @RequestBody TransactionRequest transactionRequest) {

        TransactionOutcome transactionOutcome = validationService.processTransaction(transactionRequest);
        if (!transactionOutcome.isCorrect()){
            return ResponseEntity.badRequest().body( Map.of(
                    "mensaje",transactionOutcome.getMessage(),
                    "data", transactionOutcome.getTransactionResponse()
            ));
        }
        return ResponseEntity.ok(transactionOutcome.getTransactionResponse());
    }
}
