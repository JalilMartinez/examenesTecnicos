package com.api.data_processor_api.controller;



import com.api.data_processor_api.model.dto.UserOutcome;
import com.api.data_processor_api.model.dto.UserRequest;
import com.api.data_processor_api.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth-api")
public class AuthenticatorController {
    @Autowired
    UserService userService;

    @PostMapping("/authenticateUser")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody UserRequest user){
        UserOutcome userOutcome = userService.validateUser(user);
        if(!userOutcome.isCorrect()){
            return ResponseEntity.badRequest().body( Map.of(
                    "message",userOutcome.getMessage()
            ));
        }
        return ResponseEntity.ok(userOutcome.getUser());
    }

}
