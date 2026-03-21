package com.api.validator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ValidatorApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ValidatorApiApplication.class, args);
	}

}
