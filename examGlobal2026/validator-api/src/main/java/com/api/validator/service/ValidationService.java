package com.api.validator.service;

import com.api.validator.client.FeingClient;
import com.api.validator.model.TransactionOutcome;
import com.api.validator.model.dto.TransactionRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ValidationService {
    @Autowired
    FeingClient feingClient;
    @Autowired
    HashService hashService;

    public TransactionOutcome processTransaction(TransactionRequestDto transactionRequestDto) {
        TransactionOutcome transactionOutcome = new TransactionOutcome(true);
        if (!validateKey(transactionRequestDto)){
            transactionOutcome.setCorrect(false);
            transactionOutcome.setMessage("No se pudo comprobar la integridad de los datos");
            return transactionOutcome;
        }
        transactionOutcome.setTransactionResponse(feingClient.processTransaction(transactionRequestDto));
        return transactionOutcome;
    }
    private boolean validateKey(TransactionRequestDto transactionRequestDto) {
        String forGenerateKey = transactionRequestDto.getOperacion()+"|"+ transactionRequestDto.getImporte()+"|"+ transactionRequestDto.getCliente();
        String newsha = hashService.generateSha(forGenerateKey);
        return newsha.equals(transactionRequestDto.getFirma());
    }

}
