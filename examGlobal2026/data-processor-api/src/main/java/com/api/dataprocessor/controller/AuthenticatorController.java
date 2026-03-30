package com.api.dataprocessor.controller;



import com.api.dataprocessor.model.UserOutcome;
import com.api.dataprocessor.model.dto.UserDto;
import com.api.dataprocessor.model.dto.UserRequestDto;
import com.api.dataprocessor.services.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthenticatorController {

    private final UserService userService;
    public static final Logger logger = LoggerFactory.getLogger(AuthenticatorController.class);


    public AuthenticatorController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/authenticateUser")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody UserRequestDto user){
        logger.info("[authenticateUser] Recibida solicitud para validar credenciales usuario: {}",user.getUserName());
        UserOutcome userOutcome = userService.validateUser(user);
        if(!userOutcome.isCorrect()){
            return ResponseEntity.badRequest().body( Map.of(
                    "message",userOutcome.getMessage()
            ));
        }
        logger.info("[authenticateUser] Usuario autenticado con exito, usuario: {}",user.getUserName());
        return ResponseEntity.ok(UserDto.builder()
                .userName(userOutcome.getUser().getUsername())
                .token(userOutcome.getToken())
                .message("Autenticado")
                .build());
    }

}
