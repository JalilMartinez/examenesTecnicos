package com.api.validator_api;

import com.api.validator_api.entities.TransactionRequest;
import com.api.validator_api.entities.TransactionResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "api-processor",url = "http://localhost:8081/processor-transaction-api")
public interface FeingClient {

    @PostMapping("")
    TransactionResponse processTransaction(@RequestBody TransactionRequest transactionRequest);

}
