package com.api.data_processor_api.services;

import com.api.data_processor_api.handlerException.ResourceNotFoundException;
import com.api.data_processor_api.handlerException.TransactionSaveException;
import com.api.data_processor_api.model.dto.*;
import com.api.data_processor_api.model.entity.*;
import com.api.data_processor_api.model.mapper.TransactionMapper;
import com.api.data_processor_api.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;


@Service
public class DataProcessorService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private TransactionMapper transactionMapper;

    public TransactionOutcomeResponse doTransaction(TransactionRequest transactionRequest){
        TransactionOutcomeResponse transactionOutcome = new TransactionOutcomeResponse(true);
        String referencia = generateReferencia();
        TransactionEntity entity = transactionMapper.mapRequestToTransactionEntitiy(transactionRequest,referencia);
        try {
            entity = transactionRepository.save(entity);
        }catch(Exception e){
            throw new TransactionSaveException("Ocurrio un error al guardar la transacción",e);
        }
        transactionOutcome.setTransactionResponse(transactionMapper.mapTransactionEntityToTransactionResponse(entity));
        if (transactionOutcome.getTransactionResponse().getId()==0) {
            setTransactionOutcomeError(transactionOutcome, "transccion no guardada");
        }
        return transactionOutcome;
    }

    private String generateReferencia (){
        String referencia = "";
        do{
            referencia = String.format("%06d", new Random().nextInt(1000000));
        }while(transactionRepository.existsByReferencia(referencia));
        return referencia;
    }
    private void setTransactionOutcomeError(TransactionOutcome transactionOutcome, String message){
        transactionOutcome.setCorrect(false);
        transactionOutcome.setMessage(new String(message));
    }
    public TransactionOutcomeEntityList getAllTransactions(){
        TransactionOutcomeEntityList transactionOutcome = new TransactionOutcomeEntityList(true);
        try{
            transactionOutcome.setEntityList(transactionRepository.findAll());
            if (transactionOutcome.getEntityList().isEmpty()){
                setTransactionOutcomeError(transactionOutcome,"No se pudieron obtener las transacciones");
            }
        }catch (Exception e){
            throw new ResourceNotFoundException("Error al obtener las transacciones",e);
        }
        return transactionOutcome;
    }


    public TransactionOutcomeResponse updateEstatusTransaction(EntityToUpdate entityToUpdate){
        TransactionEntity transactionEntity = transactionRepository.findById(entityToUpdate.getId()).orElseThrow(()-> new ResourceNotFoundException("Transaccion no encontrada"));
        transactionEntity.setEstatus(entityToUpdate.getEstatus());
        TransactionEntity transactionSaved;
        try {
            transactionSaved = transactionRepository.save(transactionEntity);
        }catch (Exception e){
            throw new TransactionSaveException("Ocurrio un error al guardar la modificación",e);
        }
        TransactionOutcomeResponse transactionOutcome = new TransactionOutcomeResponse(true);
        transactionOutcome.setTransactionResponse(transactionMapper.mapTransactionEntityToTransactionResponse(transactionSaved));
        return transactionOutcome;
    }
}
