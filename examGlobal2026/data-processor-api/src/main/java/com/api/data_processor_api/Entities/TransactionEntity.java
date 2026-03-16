package com.api.data_processor_api.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table (name="transacciones")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String operacion;
    private BigDecimal importe;
    private String cliente;
    private String referencia;
    private String estatus;
    private String secreto;

    public TransactionEntity(){}
    public TransactionEntity(TransactionRequest transactionRequest, String referencia){
        setEstatus("Aprobada");
        setOperacion(transactionRequest.getOperacion());
        setSecreto(transactionRequest.getFirma());
        setImporte(transactionRequest.getImporte());
        setCliente(transactionRequest.getCliente());
        setReferencia(referencia);
    }


}
