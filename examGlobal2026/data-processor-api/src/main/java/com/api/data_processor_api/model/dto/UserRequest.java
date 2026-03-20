package com.api.data_processor_api.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRequest {

    @NotBlank(message="usuario es obligatorio")
    private String userName;
    @NotBlank(message="contraseña es obligatorio")
    private String psw;

}
