package com.api.data_processor_api.services;

import com.api.data_processor_api.model.dto.UserOutcome;
import com.api.data_processor_api.model.dto.UserRequest;
import com.api.data_processor_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;


    public UserOutcome validateUser(UserRequest userRequest){
        UserOutcome userOutcome = new UserOutcome(true);
        userOutcome.setUser(userRepository.findByUserName(userRequest.getUserName()));
        if (userOutcome.getUser() == null || !this.isPassCorrect( userRequest.getPsw(),userOutcome.getUser().getPsw()) ){
            setUserOutcomeError(userOutcome);
        }
        return userOutcome;
    }
    private boolean isPassCorrect(String pswR, String pswBd){
        BCryptPasswordEncoder enconder = new BCryptPasswordEncoder();
        return enconder.matches(pswR, pswBd);
    }
    private void setUserOutcomeError(UserOutcome userOutcome){
        userOutcome.setCorrect(false);
        userOutcome.setMessage("Contraseña o usuario incorrecto");
    }

}
