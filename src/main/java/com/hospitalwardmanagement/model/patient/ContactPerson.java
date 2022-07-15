package com.hospitalwardmanagement.model.patient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactPerson {
    String firstName;
    String lastName;
    @Pattern(regexp = "^\\d{9}$", message = "Phone Number should have 9 numbers")
    String phoneNumber;
}
