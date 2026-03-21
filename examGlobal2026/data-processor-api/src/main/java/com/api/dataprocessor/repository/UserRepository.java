package com.api.dataprocessor.repository;

import com.api.dataprocessor.model.entity.User;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(@NotBlank(message="usuario es obligatorio") String userName);
}
