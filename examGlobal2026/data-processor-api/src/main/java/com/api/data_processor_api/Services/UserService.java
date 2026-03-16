package com.api.data_processor_api.Services;

import com.api.data_processor_api.Entities.User;
import com.api.data_processor_api.Entities.UserRequest;
import com.api.data_processor_api.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public boolean validateUser(UserRequest userRequest){

        User user = userRepository.findAllByUserName(userRequest.getUserName());
        if (user == null){
            return false;
        }
        BCryptPasswordEncoder enconder = new BCryptPasswordEncoder();
        return enconder.matches( userRequest.getPsw(),user.getPsw());
    }

}
