package com.api.dataprocessor.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

//   ❌ @Autowired
//   ❌ JwtAuthenticationFilter jwtAuthFilter;
    @Autowired
    AuthenticationProvider authenticationProvider;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http

                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/auth/**").permitAll() // Público para login
                        .requestMatchers("/processortransactionapi/**").permitAll() //se quita este para evitar que autentique token ❌.authenticated()
                        .anyRequest().authenticated() // ¡TODO LO DEMÁS BLOQUEADO!
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Sin sesiones
                );
        //Comentamos la validacion de token para usar auth0 en el gateway, la parte del login quedaria inutilizada
                //❌.authenticationProvider(authenticationProvider) // El que creamos antes
                //❌.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class); // <--- CLAVE

        return http.build();
    }


}