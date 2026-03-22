package com.api.dataprocessor.controller;



import com.api.dataprocessor.model.UserOutcome;
import com.api.dataprocessor.model.dto.UserRequestDto;
import com.api.dataprocessor.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthenticatorController {

    private final UserService userService;

    public AuthenticatorController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/authenticateUser")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody UserRequestDto user){
        UserOutcome userOutcome = userService.validateUser(user);
        if(!userOutcome.isCorrect()){
            return ResponseEntity.badRequest().body( Map.of(
                    "message",userOutcome.getMessage()
            ));
        }
        return ResponseEntity.ok(Map.of("user", userOutcome.getUser().getUserName()));
    }

}
