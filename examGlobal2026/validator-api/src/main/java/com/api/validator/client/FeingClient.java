package com.api.validator.client;

import com.api.validator.config.FeingErrorDecoder;
import com.api.validator.model.dto.TransactionRequestDto;
import com.api.validator.model.dto.TransactionResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "api-processor",
        url = "http://localhost:8081/processortransactionapi",
        configuration = FeingErrorDecoder.class)
public interface FeingClient {

    @PostMapping("")
    TransactionResponseDto processTransaction(@RequestBody TransactionRequestDto transactionRequestDto);

}
