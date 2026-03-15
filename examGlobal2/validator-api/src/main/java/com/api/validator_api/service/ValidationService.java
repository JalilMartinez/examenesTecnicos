package com.api.validator_api.service;

import com.api.validator_api.FeingClient;
import com.api.validator_api.entities.TransactionRequest;
import com.api.validator_api.entities.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class ValidationService {
    @Autowired
    FeingClient feingClient;

    //revisar el uso del trows, tambien pensar en pasar el generar sha a otro servico
    public void processTransaction(TransactionRequest transactionRequest, TransactionResponse transactionResponse) throws NoSuchAlgorithmException {

        if (validateKey(transactionRequest)){
            TransactionResponse transactionResponseN = feingClient.processTransaction(transactionRequest);

            transactionResponse.setId(transactionResponseN.getId());
            transactionResponse.setOperacion(transactionResponseN.getOperacion());
            transactionResponse.setEstatus(transactionResponseN.getEstatus());
            transactionResponse.setReferencia(transactionResponseN.getReferencia());
        }
    }
    private boolean validateKey(TransactionRequest transactionRequest) throws NoSuchAlgorithmException {
        String forGenerateKey =transactionRequest.getOperacion()+"|"+transactionRequest.getImporte()+"|"+transactionRequest.getCliente();
        String newsha = generateSha(forGenerateKey);
        System.out.println(newsha);
        return newsha.equals(transactionRequest.getFirma());
    }

    private static String generateSha(String forGenerateKey) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] sha = md.digest(forGenerateKey.getBytes());

        StringBuilder hasString = new StringBuilder();
        for (byte b:sha){
            hasString.append(String.format("%02x",b));
        }
        return hasString.toString();
    }
}
