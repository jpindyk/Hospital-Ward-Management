package com.hospitalwardmanagement.payload;

import com.hospitalwardmanagement.model.patient.Address;
import com.hospitalwardmanagement.model.patient.ContactPerson;
import com.hospitalwardmanagement.model.patient.MaritalStatus;
import lombok.Data;
import org.hibernate.validator.constraints.pl.PESEL;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class PatientDTO {

    @NotBlank
    private String firstName;

    @NotBlank
    private String LastName;


    @Valid
    private Address homeAddress;


    private Address correspondenceAddress;

    @PESEL
    private String pesel;

    @Pattern(regexp = "^\\d{9}$", message = "Phone Number should have 9 numbers")
    private String phoneNumber;

    @Email
    private String email;

    private String job;


    private MaritalStatus maritalStatus;

    @Valid
    private ContactPerson contactPerson;
}
