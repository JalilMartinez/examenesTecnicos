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
    private Long referencia;
    private String status;
    private String secreto;


}
