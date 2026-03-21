package com.api.validator.model.dto;

import lombok.Data;

@Data
public class TransactionResponseDto {
    private long id;
    private String estatus;
    private String referencia;
    private String operacion;

}
