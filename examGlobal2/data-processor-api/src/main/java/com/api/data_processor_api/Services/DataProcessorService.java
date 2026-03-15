package com.api.data_processor_api.Services;

import com.api.data_processor_api.Entities.EntityToUpdate;
import com.api.data_processor_api.Entities.TransactionRequest;
import com.api.data_processor_api.Entities.TransactionResponse;
import com.api.data_processor_api.Entities.TransactionEntity;
import com.api.data_processor_api.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Random;


@Service
public class DataProcessorService {

    @Autowired
    private TransactionRepository transactionRepository;

    public void doTransaction(TransactionResponse transactionResponse, TransactionRequest transactionRequest){
        String referencia = generateReferencia();
        TransactionEntity entity = new TransactionEntity(transactionRequest,referencia);
        TransactionEntity savedTransaction = transactionRepository.save(entity);
        transactionResponse.setReferencia(savedTransaction.getReferencia());
        transactionResponse.setOperacion(savedTransaction.getOperacion());
        transactionResponse.setEstatus(savedTransaction.getEstatus());
        transactionResponse.setId(savedTransaction.getId());
    }

    private String generateReferencia (){
        String referencia = "";
        do{
            referencia = String.format("%06d", new Random().nextInt(1000000));
        }while(transactionRepository.existsByReferencia(referencia));
        return referencia;
    }

    public void getAllTransactions(List<TransactionEntity> transactionEntityList){
        transactionEntityList.addAll(transactionRepository.findAll());
    }

    public void updateEstatusTransaction( EntityToUpdate entityToUpdate, TransactionResponse transactionResponse){
        TransactionEntity transactionEntity = transactionRepository.findById(entityToUpdate.getId()).orElseThrow(()-> new RuntimeException("Transaccion no encontrada"));
        transactionEntity.setEstatus(entityToUpdate.getEstatus());
        TransactionEntity transactionSaved = transactionRepository.save(transactionEntity);
        transactionResponse.setId(transactionSaved.getId());
        transactionResponse.setEstatus(transactionSaved.getEstatus());
        transactionResponse.setOperacion(transactionSaved.getOperacion());
        transactionResponse.setReferencia(transactionSaved.getReferencia());
    }
}
