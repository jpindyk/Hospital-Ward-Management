package com.hospitalwardmanagement.payload;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class HospitalRoomDTO {
    private String name;
    @Min(value = 3, message = "Hospital room must have at least 3 beds")
    @Max(value = 10, message = "Hospital room must have maximum 10 beds")
    private Integer capacity;
}
