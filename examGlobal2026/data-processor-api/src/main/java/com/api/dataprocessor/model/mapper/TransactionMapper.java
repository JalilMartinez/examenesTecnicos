package com.api.dataprocessor.model.mapper;

import com.api.dataprocessor.model.dto.TransactionRequestDto;
import com.api.dataprocessor.model.dto.TransactionResponseDto;
import com.api.dataprocessor.model.entity.TransactionEntity;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

    public TransactionResponseDto mapTransactionEntityToTransactionResponse(TransactionEntity transactionEntity){
        TransactionResponseDto transactionResponseDto = new TransactionResponseDto();
        transactionResponseDto.setReferencia(transactionEntity.getReferencia());
        transactionResponseDto.setOperacion(transactionEntity.getOperacion());
        transactionResponseDto.setEstatus(transactionEntity.getEstatus());
        transactionResponseDto.setId(transactionEntity.getId());
        return transactionResponseDto;
    }

    public TransactionEntity mapRequestToTransactionEntitiy(TransactionRequestDto transactionRequestDto, String referencia){
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setEstatus("Aprobada");
        transactionEntity.setOperacion(transactionRequestDto.getOperacion());
        transactionEntity.setSecreto(transactionRequestDto.getFirma());
        transactionEntity.setImporte(transactionRequestDto.getImporte());
        transactionEntity.setCliente(transactionRequestDto.getCliente());
        transactionEntity.setReferencia(referencia);
        return transactionEntity;
    }
}
