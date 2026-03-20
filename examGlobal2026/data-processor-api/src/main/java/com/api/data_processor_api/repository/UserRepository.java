package com.api.data_processor_api.repository;

import com.api.data_processor_api.model.entity.User;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(@NotBlank(message="usuario es obligatorio") String userName);
}
