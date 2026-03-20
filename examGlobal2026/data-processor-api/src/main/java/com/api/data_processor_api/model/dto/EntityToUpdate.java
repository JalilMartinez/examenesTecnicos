package com.api.data_processor_api.model.dto;

import lombok.Data;

@Data
public class EntityToUpdate {
    private Long id;
    private String referencia;
    private String estatus;
}
