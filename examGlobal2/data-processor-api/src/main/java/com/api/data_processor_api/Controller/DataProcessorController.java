package com.api.data_processor_api.Controller;


import com.api.data_processor_api.Entities.TransactionRequest;
import com.api.data_processor_api.Entities.TransactionResponse;
import com.api.data_processor_api.Services.DataProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}