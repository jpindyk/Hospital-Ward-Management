package com.hospitalwardmanagement.model.patient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.Digits;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactPerson {
    String firstName;
    String lastName;
    @Digits(integer = 9, fraction = 0)
    String phoneNumber;
}
