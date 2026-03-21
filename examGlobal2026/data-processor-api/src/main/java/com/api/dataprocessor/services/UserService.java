package com.api.dataprocessor.services;

import com.api.dataprocessor.model.UserOutcome;
import com.api.dataprocessor.model.dto.UserRequestDto;
import com.api.dataprocessor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;


    public UserOutcome validateUser(UserRequestDto userRequestDto){
        UserOutcome userOutcome = new UserOutcome(true);
        if (Objects.isNull(userRequestDto.getUserName())){
            setUserOutcomeError(userOutcome,"Datos de peticion vacios");
            return userOutcome;
        }
        userOutcome.setUser(userRepository.findByUserName(userRequestDto.getUserName()));
        if (userOutcome.getUser() == null || !this.isPassCorrect( userRequestDto.getPsw(),userOutcome.getUser().getPsw()) ){
            setUserOutcomeError(userOutcome,"Contraseña o usuario incorrecto");
        }
        return userOutcome;
    }
    private boolean isPassCorrect(String pswR, String pswBd){
        BCryptPasswordEncoder enconder = new BCryptPasswordEncoder();
        return enconder.matches(pswR, pswBd);
    }
    private void setUserOutcomeError(UserOutcome userOutcome, String mensaje){
        userOutcome.setCorrect(false);
        userOutcome.setMessage(mensaje);
    }

}
