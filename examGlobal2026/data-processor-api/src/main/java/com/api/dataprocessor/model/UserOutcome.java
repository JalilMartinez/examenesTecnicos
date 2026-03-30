package com.api.dataprocessor.model;

import com.api.dataprocessor.model.entity.User;
import lombok.Data;

@Data
public class UserOutcome {
    private boolean correct;
    private User user;
    private StringBuilder message;
    private String token;


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
