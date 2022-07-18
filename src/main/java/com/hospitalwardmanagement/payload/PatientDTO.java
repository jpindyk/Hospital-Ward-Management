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

    @AttributeOverrides({
            @AttributeOverride(name = "city",column = @Column(name = "HOME_CITY")),
            @AttributeOverride(name = "street", column = @Column(name = "HOME_STREET")),
            @AttributeOverride(name = "apartmentNo", column = @Column(name = "HOME_APARTMENT_NO")),
            @AttributeOverride(name = "postalCode", column = @Column(name = "HOME_POSTAL_CODE"))
    })
    @Valid
    private Address homeAddress;

    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "CORRESPONDENCE_CITY")),
            @AttributeOverride(name = "street", column = @Column(name = "CORRESPONDENCE_STREET")),
            @AttributeOverride(name = "apartmentNo", column = @Column(name = "CORRESPONDENCE_APARTMENT_NO")),
            @AttributeOverride(name = "postalCode", column = @Column(name = "CORRESPONDENCE_POSTAL_CODE"))
    })
    private Address correspondenceAddress;

    @PESEL
    private String pesel;

    @Pattern(regexp = "^\\d{9}$", message = "Phone Number should have 9 numbers")
    private String phoneNumber;

    @Email
    private String email;

    private String job;

    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;

    @AttributeOverrides({
            @AttributeOverride(name = "firstName", column = @Column(name = "CONTACT_PERSON_FIRST_NAME")),
            @AttributeOverride(name = "lastName", column = @Column(name = "CONTACT_PERSON_LAST_NAME")),
            @AttributeOverride(name = "phoneNumber", column = @Column(name = "CONTACT_PERSON_PHONE_NUMBER"))
    })
    @Valid
    private ContactPerson contactPerson;
}
