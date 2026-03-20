package com.api.validator_api.service;

import com.api.validator_api.client.FeingClient;
import com.api.validator_api.handlerException.AlgorithShaException;
import com.api.validator_api.model.dto.TransactionOutcome;
import com.api.validator_api.model.dto.TransactionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class ValidationService {
    @Autowired
    FeingClient feingClient;


    public TransactionOutcome processTransaction(TransactionRequest transactionRequest) {
        TransactionOutcome transactionOutcome = new TransactionOutcome(true);
        if (!validateKey(transactionRequest)){
            transactionOutcome.setCorrect(false);
            transactionOutcome.setMessage("No se pudo comprobar la integridad de los datos");
            return transactionOutcome;
        }
        transactionOutcome.setTransactionResponse(feingClient.processTransaction(transactionRequest));
        return transactionOutcome;
    }
    private boolean validateKey(TransactionRequest transactionRequest) {
        String forGenerateKey =transactionRequest.getOperacion()+"|"+transactionRequest.getImporte()+"|"+transactionRequest.getCliente();
        String newsha = generateSha(forGenerateKey);
        return newsha.equals(transactionRequest.getFirma());
    }

    private static String generateSha(String forGenerateKey){
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] sha = md.digest(forGenerateKey.getBytes());

            StringBuilder hasString = new StringBuilder();
            for (byte b:sha){
                hasString.append(String.format("%02x",b));
            }
            return hasString.toString();
        }catch (NoSuchAlgorithmException e) {
            throw new AlgorithShaException("Algoritmo de cifrado no disponible",e);
        }

    }
}
