package com.api.validator_api.entities;

import lombok.Data;

@Data
public class TransactionResponse {
    private long id;
    private String estatus;
    private String referencia;
    private String operacion;

}
