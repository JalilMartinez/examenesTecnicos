package com.api.validator.controller;

import com.api.validator.model.TransactionOutcome;
import com.api.validator.model.dto.TransactionRequestDto;
import com.api.validator.service.ValidationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/savetransactionapi")
public class ValidatorController {

    private final ValidationService validationService;

    public ValidatorController(ValidationService validationService){
        this.validationService = validationService;
    }

    @PostMapping("")
    public ResponseEntity<?> doTransaction(@Valid @RequestBody TransactionRequestDto transactionRequestDto) {

        TransactionOutcome transactionOutcome = validationService.processTransaction(transactionRequestDto);
        if (!transactionOutcome.isCorrect()){
            return ResponseEntity.badRequest().body( Map.of(
                    "mensaje",transactionOutcome.getMessage(),
                    "data", transactionOutcome.getTransactionResponse()
            ));
        }
        return ResponseEntity.ok(transactionOutcome.getTransactionResponse());
    }
}
