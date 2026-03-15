package com.api.data_processor_api.Services;

import com.api.data_processor_api.Entities.TransactionRequest;
import com.api.data_processor_api.Entities.TransactionResponse;
import com.api.data_processor_api.Entities.TransactionEntity;
import com.api.data_processor_api.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;


@Service
public class DataProcessorService {

    @Autowired
    private TransactionRepository transactionRepository;

    public void getOperation(TransactionResponse transactionResponse, String operRequestJson){
        ObjectMapper objectMapper = new ObjectMapper();
        TransactionRequest transactionRequest = new TransactionRequest();
        try{
            transactionRequest =objectMapper.readValue(operRequestJson, TransactionRequest.class);
        }catch (Exception e){
            System.out.println(e);
        }


    }

    public void getSum(TransactionResponse transactionResponse, TransactionRequest transactionRequest, String operRequestJson){
        double sumaR= transactionRequest.getNumero_1()+ transactionRequest.getNumero_2();
        transactionRepository.save(new TransactionEntity(transactionRequest.getId(),operRequestJson,sumaR));
        TransactionEntity transactionEntity = transactionRepository.getReferenceById((long) transactionRequest.getId());
        transactionResponse.setResultado(transactionEntity.getResultado());
        transactionResponse.setId(transactionEntity.getId());
    }
}
