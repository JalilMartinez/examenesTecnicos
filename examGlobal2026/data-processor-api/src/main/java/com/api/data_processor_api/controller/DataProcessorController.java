package com.api.data_processor_api.controller;


import com.api.data_processor_api.model.dto.*;
import com.api.data_processor_api.model.entity.*;
import com.api.data_processor_api.services.DataProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/processor-transaction-api")
public class DataProcessorController {

    @Autowired
    DataProcessorService dataProcessorService;

    @PostMapping("")
    public ResponseEntity<?> processTransaction (@RequestBody TransactionRequest transactionRequest){
        TransactionOutcomeResponse transactionOutcome = dataProcessorService.doTransaction(transactionRequest);
        if (!transactionOutcome.isCorrect()){
            return ResponseEntity.badRequest().body( Map.of(
                    "message",transactionOutcome.getMessage()
            ));
        }
        return ResponseEntity.ok(transactionOutcome.getTransactionResponse());
    }

    @GetMapping("/getAllTransactions")
    public ResponseEntity<?> getAllTransactions(){
        TransactionOutcomeEntityList transactionOutcome = dataProcessorService.getAllTransactions();
        if (!transactionOutcome.isCorrect()){
            return ResponseEntity.badRequest().body( Map.of(
                    "message",transactionOutcome.getMessage()
            ));
        }
        return ResponseEntity.ok(transactionOutcome.getEntityList());
    }

    @PatchMapping("/updateEstatusTransaction")
    public ResponseEntity<?> updateEstatusTransaction(@RequestBody EntityToUpdate entityToUpdate){
        TransactionOutcomeResponse transactionOutcome = dataProcessorService.updateEstatusTransaction(entityToUpdate);
        if (!transactionOutcome.isCorrect()){
            return ResponseEntity.badRequest().body( Map.of(
                    "message",transactionOutcome.getMessage()
            ));
        }
        return ResponseEntity.ok(transactionOutcome.getTransactionResponse());
    }

}