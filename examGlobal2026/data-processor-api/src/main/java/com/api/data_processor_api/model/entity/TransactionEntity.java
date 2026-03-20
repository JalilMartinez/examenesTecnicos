package com.api.data_processor_api.model.entity;

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

}
