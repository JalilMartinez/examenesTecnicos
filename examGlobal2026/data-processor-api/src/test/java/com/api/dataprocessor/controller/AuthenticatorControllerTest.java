package com.api.dataprocessor.controller;

import com.api.dataprocessor.model.dto.UserDto;
import com.api.dataprocessor.model.dto.UserRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AuthenticatorControllerTest {
    @Autowired
    private  AuthenticatorController authenticatorController;

    @Test
    public void authenticateUserTest(){
        UserRequestDto user = new UserRequestDto();
        user.setUserName("Jalil");
        user.setPsw("12345");

        ResponseEntity<?> response = authenticatorController.authenticateUser(user);

        assertEquals(HttpStatus.OK, response.getStatusCode(), "El estatus de la peticion no es correcto");

        UserDto userBody = (UserDto) response.getBody();

        assertEquals("Jalil", userBody.getUserName(), "usuario diferente");
    }
    @Test
    public void authenticateUserFailTest(){
        UserRequestDto user = new UserRequestDto();
        user.setUserName("Jalil");
        user.setPsw("123435");

        assertThrows(Exception.class, () -> {
            authenticatorController.authenticateUser(user);
        });
    }
    @Test
    public void authenticateUserEmptyTest(){
        UserRequestDto user = new UserRequestDto();
        ResponseEntity<?> response = authenticatorController.authenticateUser(user);
        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode(),"El estatus de la peticion no es correcto");
        Map<String,String> body = (Map<String,String>) response.getBody();
        assertEquals("Datos de peticion vacios",body.get("message"),"Mensaje error no coincide");
    }
}
