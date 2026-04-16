package com.authentication.authentication.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/public/hello")
    public String publicEndpoint() {
        return "Hola, este endpoint es público 👌";
    }

    @GetMapping("/private/hello")
    public String privateEndpoint(@AuthenticationPrincipal Jwt jwt) {
        return "Hola usuario autenticado: " + jwt.getSubject();
    }
}