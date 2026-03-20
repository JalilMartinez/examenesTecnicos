package com.api.data_processor_api.model.mapper;

import com.api.data_processor_api.model.dto.TransactionRequest;
import com.api.data_processor_api.model.dto.TransactionResponse;
import com.api.data_processor_api.model.entity.TransactionEntity;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

    public TransactionResponse mapTransactionEntityToTransactionResponse(TransactionEntity transactionEntity){
        TransactionResponse transactionResponse = new TransactionResponse();
        transactionResponse.setReferencia(transactionEntity.getReferencia());
        transactionResponse.setOperacion(transactionEntity.getOperacion());
        transactionResponse.setEstatus(transactionEntity.getEstatus());
        transactionResponse.setId(transactionEntity.getId());
        return transactionResponse;
    }

    public TransactionEntity mapRequestToTransactionEntitiy(TransactionRequest transactionRequest, String referencia){
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setEstatus("Aprobada");
        transactionEntity.setOperacion(transactionRequest.getOperacion());
        transactionEntity.setSecreto(transactionRequest.getFirma());
        transactionEntity.setImporte(transactionRequest.getImporte());
        transactionEntity.setCliente(transactionRequest.getCliente());
        transactionEntity.setReferencia(referencia);
        return transactionEntity;
    }
}
