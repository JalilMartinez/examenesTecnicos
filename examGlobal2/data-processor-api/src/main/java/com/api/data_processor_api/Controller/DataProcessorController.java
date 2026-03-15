package com.api.data_processor_api.Controller;


import com.api.data_processor_api.Entities.EntityToUpdate;
import com.api.data_processor_api.Entities.TransactionEntity;
import com.api.data_processor_api.Entities.TransactionRequest;
import com.api.data_processor_api.Entities.TransactionResponse;
import com.api.data_processor_api.Services.DataProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/processor-transaction-api")
public class DataProcessorController {

    @Autowired
    DataProcessorService dataProcessorService;

    @PostMapping("")
    private ResponseEntity<TransactionResponse> processTransaction (@RequestBody TransactionRequest transactionRequest){
        TransactionResponse transactionResponse = new TransactionResponse();
        dataProcessorService.doTransaction(transactionResponse,transactionRequest);
        System.out.println(transactionResponse.getId());
        return ResponseEntity.ok(transactionResponse);
    }

    @GetMapping("/getAllTransactions")
    private ResponseEntity<List<TransactionEntity>> getAllTransactions(){
        List<TransactionEntity> transactionEntityList = new ArrayList<>();
        dataProcessorService.getAllTransactions(transactionEntityList);
        return ResponseEntity.ok(transactionEntityList);
    }

    @PatchMapping("/updateEstatusTransaction")
    private ResponseEntity<TransactionResponse> updateEstatusTransaction(@RequestBody EntityToUpdate entityToUpdate){
        TransactionResponse transactionResponse = new TransactionResponse();
        dataProcessorService.updateEstatusTransaction(entityToUpdate,transactionResponse);
        return ResponseEntity.ok(transactionResponse);
    }

}