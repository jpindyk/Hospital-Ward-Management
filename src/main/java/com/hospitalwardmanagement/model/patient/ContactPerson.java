package com.hospitalwardmanagement.model.patient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactPerson {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @Pattern(regexp = "^\\d{9}$", message = "Phone Number should have 9 numbers")
    private String phoneNumber;
}
