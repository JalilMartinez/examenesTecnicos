package com.api.data_processor_api.model.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionRequest {
    @NotBlank(message="operacion es obligatorio")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "operacion solo puede contener caracteres a-z")
    @Size(max=50,message = "operacion no debe superar los 50 caracteres")
    private String operacion;

    @NotBlank(message="immporte es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "importe debe ser mayor a 0")
    @Digits(integer = 10, fraction = 3, message = "El importe debe tener maximo 10 enteros y 3 decimales")
    private BigDecimal importe;

    @NotBlank(message="cliente  es obligatorio")
    @Size(max=50,message = "cliente no debe superar los 50 caracteres")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "cliente solo puede contener caracteres a-z")
    private String cliente;

    @NotBlank(message="firma es obligatoria")
    private String firma;

}
