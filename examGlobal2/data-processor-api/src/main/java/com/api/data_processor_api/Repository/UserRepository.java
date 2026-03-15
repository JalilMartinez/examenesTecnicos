package com.api.data_processor_api.Repository;

import com.api.data_processor_api.Entities.User;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findAllByUserName(@NotBlank(message="usuario es obligatorio") String userName);
}
