package com.api.dataprocessor.model.dto;

import lombok.Data;

@Data
public class TransactionResponseDto {
    private long id;
    private String estatus;
    private String referencia;
    private String operacion;

}
