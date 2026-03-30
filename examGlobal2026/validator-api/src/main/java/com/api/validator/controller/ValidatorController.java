package com.api.validator.controller;

import com.api.validator.model.TransactionOutcome;
import com.api.validator.model.dto.TransactionRequestDto;
import com.api.validator.service.ValidationService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/validate")
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
        System.out.println("listo");
        return ResponseEntity.ok(transactionOutcome.getMessage());
    }
}
