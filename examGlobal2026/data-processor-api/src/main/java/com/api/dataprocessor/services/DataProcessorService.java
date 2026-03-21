package com.api.dataprocessor.services;

import com.api.dataprocessor.handlerException.ResourceNotFoundException;
import com.api.dataprocessor.handlerException.TransactionSaveException;
import com.api.dataprocessor.model.EntityToUpdate;
import com.api.dataprocessor.model.TransactionOutcome;
import com.api.dataprocessor.model.dto.TransactionOutcomeEntityListDto;
import com.api.dataprocessor.model.TransactionOutcomeResponse;
import com.api.dataprocessor.model.dto.*;
import com.api.dataprocessor.model.entity.*;
import com.api.dataprocessor.model.mapper.TransactionMapper;
import com.api.dataprocessor.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Random;


@Service
public class DataProcessorService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private TransactionMapper transactionMapper;

    public TransactionOutcomeResponse doTransaction(TransactionRequestDto transactionRequestDto){
        TransactionOutcomeResponse transactionOutcome = new TransactionOutcomeResponse(true);
        if(Objects.isNull(transactionRequestDto.getFirma())){
            setTransactionOutcomeError(transactionOutcome, "Cuerpo de transacción vacio");
            return transactionOutcome;
        }
        String referencia = generateReferencia();
        TransactionEntity entity = transactionMapper.mapRequestToTransactionEntitiy(transactionRequestDto,referencia);
        try {
            entity = transactionRepository.save(entity);
        }catch(Exception e){
            throw new TransactionSaveException("Ocurrio un error al guardar la transacción",e);
        }
        transactionOutcome.setTransactionResponseDto(transactionMapper.mapTransactionEntityToTransactionResponse(entity));
        if (transactionOutcome.getTransactionResponseDto().getId()==0) {
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
    public TransactionOutcomeEntityListDto getAllTransactions(int page, int size){
        TransactionOutcomeEntityListDto transactionOutcome = new TransactionOutcomeEntityListDto(true);
        if(size==0){
            setTransactionOutcomeError(transactionOutcome, "Paginador vacio");
        }
        Pageable pageable = PageRequest.of(page,size);
        try{
            Page<TransactionEntity> transactionEntities = transactionRepository.findAll(pageable);
            transactionOutcome.setEntityList(transactionEntities.getContent());
            if (transactionOutcome.getEntityList().isEmpty()){
                setTransactionOutcomeError(transactionOutcome,"No se pudieron obtener las transacciones");
            }
            transactionOutcome.setTotalPages(transactionEntities.getTotalPages());
            transactionOutcome.setCurrentPage(transactionEntities.getNumber());
            transactionOutcome.setTotalElements(transactionEntities.getTotalElements());
        }catch (Exception e){
            throw new ResourceNotFoundException("Error al obtener las transacciones",e);
        }
        return transactionOutcome;
    }


    public TransactionOutcomeResponse updateEstatusTransaction(EntityToUpdate entityToUpdate){
        TransactionOutcomeResponse transactionOutcome = new TransactionOutcomeResponse(true);
        if(Objects.isNull(entityToUpdate.getEstatus())){
            setTransactionOutcomeError(transactionOutcome,"Datos peticion update vacios");
            return transactionOutcome;
        }
        TransactionEntity transactionEntity = transactionRepository.findById(entityToUpdate.getId()).orElseThrow(()-> new ResourceNotFoundException("Transaccion no encontrada"));
        transactionEntity.setEstatus(entityToUpdate.getEstatus());
        TransactionEntity transactionSaved;
        try {
            transactionSaved = transactionRepository.save(transactionEntity);
        }catch (Exception e){
            throw new TransactionSaveException("Ocurrio un error al guardar la modificación",e);
        }

        transactionOutcome.setTransactionResponseDto(transactionMapper.mapTransactionEntityToTransactionResponse(transactionSaved));
        return transactionOutcome;
    }
}
