package com.hospitalwardmanagement.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DoctorDTO {
    @NotBlank(message = "Doctor's first name should not be null or empty")
    private String firstName;
    @NotBlank(message = "Doctor's last name should not be null or empty")
    private String lastName;
    private String specialization;
}
