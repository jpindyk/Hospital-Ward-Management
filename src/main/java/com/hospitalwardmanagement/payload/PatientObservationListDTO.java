package com.hospitalwardmanagement.payload;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class PatientObservationListDTO {
    private String bloodPressure;
    private String bodyTemperature;
    @Size(min=25, message = "Summary should have at least 25 characters")
    private String shortSummary;
}
