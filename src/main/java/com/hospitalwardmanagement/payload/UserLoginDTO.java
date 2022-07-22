package com.hospitalwardmanagement.payload;

import lombok.Data;

@Data
public class UserLoginDTO {
    private String email;
    private String password;
}
