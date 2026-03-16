package com.api.data_processor_api.Controller;


import com.api.data_processor_api.Entities.UserRequest;
import com.api.data_processor_api.Services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth-api")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthenticatorController {
    @Autowired
    UserService userService;

    @PostMapping("/authenticateUser")
    private ResponseEntity<Boolean> authenticateUser(@Valid @RequestBody UserRequest user){
        return ResponseEntity.ok( userService.validateUser(user));
    }

}
