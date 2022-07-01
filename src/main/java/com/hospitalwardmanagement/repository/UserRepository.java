package com.hospitalwardmanagement.repository;

import com.hospitalwardmanagement.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
