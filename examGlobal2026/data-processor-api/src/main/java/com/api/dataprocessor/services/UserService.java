package com.api.dataprocessor.services;

import com.api.dataprocessor.handlerException.AuthException;
import com.api.dataprocessor.model.UserOutcome;
import com.api.dataprocessor.model.dto.UserRequestDto;
import com.api.dataprocessor.model.entity.User;
import com.api.dataprocessor.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.Objects;
@Slf4j
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtService jwtService;
    @Autowired
    AuthenticationManager authenticationManager;

    public UserOutcome validateUser(UserRequestDto userRequestDto) {
        UserOutcome userOutcome = new UserOutcome(true);
        if (Objects.isNull(userRequestDto.getUserName())){
            log.error("[validateUser] Datos de peticion para autenticarcion vacios o nulos.");
            setUserOutcomeError(userOutcome,"Datos de peticion vacios");
            return userOutcome;
        }
        Authentication auth;
        try {
            log.info("[authenticationManager.authenticate] Autenticando usuario : {}", userRequestDto.getUserName());
            auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userRequestDto.getUserName(),
                            userRequestDto.getPsw()
                    )
            );
        } catch (BadCredentialsException | InternalAuthenticationServiceException e) {
            // Estos errores son específicamente por datos del usuario (o usuario no encontrado)
            log.warn("Fallo de login: Credenciales incorrectas para {}", userRequestDto.getUserName());
            throw new AuthException("Credenciales inválidas", e);
        } catch (AuthenticationException e) {
            // Cualquier otro error de Spring Security que no sea de credenciales (ej. cuenta bloqueada, error de conexión)
            log.error("Error de seguridad interno: ", e);
            throw new AuthException("Error del sistema. Intente más tarde.", e);
        } catch (Exception e) {
            log.error("Error crítico no controlado: ", e);
            throw new AuthException("Error del sistema.", e);
        }
        User user = (User) auth.getPrincipal();
        userOutcome.setUser(user);
        userOutcome.setMessage("login correcto");
        userOutcome.setToken(jwtService.generateToken(userOutcome.getUser()));
        return userOutcome;
    }

    private void setUserOutcomeError(UserOutcome userOutcome, String mensaje){
        userOutcome.setCorrect(false);
        userOutcome.setMessage(mensaje);
    }

}
