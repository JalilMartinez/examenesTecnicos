package com.api.data_processor_api.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="userPass")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(unique = true, nullable = false)
    private String userName;
    private String psw;

    public User() {}

    public User(String userName, String psw) {
        this.userName = userName;
        this.psw = psw;
    }

}
