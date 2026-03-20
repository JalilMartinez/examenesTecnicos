package com.api.validator_api.client;

import com.api.validator_api.config.FeingErrorDecoder;
import com.api.validator_api.model.dto.TransactionRequest;
import com.api.validator_api.model.dto.TransactionResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "api-processor",
        url = "http://localhost:8081/processor-transaction-api",
        configuration = FeingErrorDecoder.class)
public interface FeingClient {

    @PostMapping("")
    TransactionResponse processTransaction(@RequestBody TransactionRequest transactionRequest);

}
