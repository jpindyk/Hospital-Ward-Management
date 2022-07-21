package com.hospitalwardmanagement.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
@Getter
public class UserAlreadyRegistered extends RuntimeException{

    private String email;

    public UserAlreadyRegistered(String email) {
        super(String.format("User with this email: " + email +  " is already registered!"));

        this.email = email;
    }

}
