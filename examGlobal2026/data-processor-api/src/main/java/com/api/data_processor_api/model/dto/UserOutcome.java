package com.api.data_processor_api.model.dto;

import com.api.data_processor_api.model.entity.User;
import lombok.Data;

@Data
public class UserOutcome {
    private boolean correct;
    private User user;
    private StringBuilder message;

    public UserOutcome(){}
    public UserOutcome(boolean isCorrect){
        this.setCorrect(isCorrect);
        this.message = new StringBuilder();
    }

    public void setMessage(String messaje){
        this.message.append(messaje);
    }
    public String getMessage(){
        return this.message.toString();
    }
}
