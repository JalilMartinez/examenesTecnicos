package com.api.dataprocessor.controller;


import com.api.dataprocessor.model.EntityToUpdate;
import com.api.dataprocessor.model.dto.TransactionOutcomeEntityListDto;
import com.api.dataprocessor.model.TransactionOutcomeResponse;
import com.api.dataprocessor.model.dto.*;
import com.api.dataprocessor.services.DataProcessorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Map;

@RestController
@RequestMapping("/processor-transaction-api")
public class DataProcessorController {

    @Autowired
    DataProcessorService dataProcessorService;

    @PostMapping("")
    public ResponseEntity<?> processTransaction (@Valid @RequestBody TransactionRequestDto transactionRequestDto){
        TransactionOutcomeResponse transactionOutcome = dataProcessorService.doTransaction(transactionRequestDto);
        if (!transactionOutcome.isCorrect()){
            return ResponseEntity.badRequest().body( Map.of(
                    "message",transactionOutcome.getMessage()
            ));
        }
        return ResponseEntity.ok(transactionOutcome.getTransactionResponseDto());
    }

    @GetMapping("/getAllTransactions")
    public ResponseEntity<?> getAllTransactions(@RequestParam int page, @RequestParam int size){
        TransactionOutcomeEntityListDto transactionOutcome = dataProcessorService.getAllTransactions(page,size);
        if (!transactionOutcome.isCorrect()){
            return ResponseEntity.badRequest().body( Map.of(
                    "message",transactionOutcome.getMessage()
            ));
        }
        return ResponseEntity.ok(transactionOutcome);
    }

    @PatchMapping("/updateEstatusTransaction")
    public ResponseEntity<?> updateEstatusTransaction(@RequestBody EntityToUpdate entityToUpdate){
        TransactionOutcomeResponse transactionOutcome = dataProcessorService.updateEstatusTransaction(entityToUpdate);
        if (!transactionOutcome.isCorrect()){
            return ResponseEntity.badRequest().body( Map.of(
                    "message",transactionOutcome.getMessage()
            ));
        }
        return ResponseEntity.ok(transactionOutcome.getTransactionResponseDto());
    }

}