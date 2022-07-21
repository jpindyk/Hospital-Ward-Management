package com.hospitalwardmanagement.payload;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserDTO {
    @NotBlank(message = "Please enter first name")
    private String firstName;
    @NotBlank(message = "Please enter last name")
    private String lastName;
    @NotBlank(message = "Please enter email")
    @Email(message = "Please enter valid email")
    private String email;
    @NotBlank(message = "Please enter password")
    @Size(min=5, message = "Password should have at least 5 characters")
    private String password;

}
