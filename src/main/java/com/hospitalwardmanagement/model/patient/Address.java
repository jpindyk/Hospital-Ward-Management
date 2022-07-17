package com.hospitalwardmanagement.model.patient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @NotBlank
    private String city;
    @NotBlank
    private String street;
    @NotBlank
    private String apartmentNo;
    @NotBlank
    private String postalCode;

}
