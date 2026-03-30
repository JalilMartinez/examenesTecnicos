package com.api.dataprocessor.model.mapper;

import com.api.dataprocessor.model.UserOutcome;
import com.api.dataprocessor.model.dto.UserDto;

public class UserMapper {
    public UserDto userOutcomeToUserDto(UserOutcome userOutcome){
        UserDto user = new UserDto();
        user.setUserName(userOutcome.getUser().getUsername());
        user.setToken(userOutcome.getToken());
        user.setMessage("autenticacion correcta");
        return user;
    }
}
