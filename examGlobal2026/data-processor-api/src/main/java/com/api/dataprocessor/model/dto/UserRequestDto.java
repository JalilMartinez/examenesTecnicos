package com.api.dataprocessor.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRequestDto {

    @NotBlank(message="usuario es obligatorio")
    private String userName;
    @NotBlank(message="contraseña es obligatorio")
    private String psw;

}
