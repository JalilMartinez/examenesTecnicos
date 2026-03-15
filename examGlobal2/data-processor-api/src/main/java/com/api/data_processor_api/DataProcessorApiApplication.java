package com.api.data_processor_api;

import com.api.data_processor_api.Entities.User;
import com.api.data_processor_api.Repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DataProcessorApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataProcessorApiApplication.class, args);
	}
    @Bean
    public CommandLineRunner initDatabase(UserRepository userRepository) {
        return args -> {
            if (userRepository.count() == 0) {
                userRepository.save(new User("Jalil", "12345"));
                userRepository.save(new User("Mazatl", "JalMaz12345"));
            }
        };
    }

}

