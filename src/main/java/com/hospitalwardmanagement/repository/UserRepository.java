package com.hospitalwardmanagement.repository;

import com.hospitalwardmanagement.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail (String email);
    Optional<User> findByEmail (String email);

}
