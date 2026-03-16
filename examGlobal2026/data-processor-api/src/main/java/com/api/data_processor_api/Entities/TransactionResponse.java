package com.api.data_processor_api.Entities;

import lombok.Data;

@Data
public class TransactionResponse {
    private long id;
    private String estatus;
    private String referencia;
    private String operacion;

}
