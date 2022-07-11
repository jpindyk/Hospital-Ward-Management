package com.hospitalwardmanagement.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
@Getter
public class HospitalRoomFullException extends RuntimeException {
    private Long fieldValue;


    public HospitalRoomFullException(Long fieldValue) {
        super(String.format("Hospital Room with id: %s is full", fieldValue));

        this.fieldValue = fieldValue;
    }
}
