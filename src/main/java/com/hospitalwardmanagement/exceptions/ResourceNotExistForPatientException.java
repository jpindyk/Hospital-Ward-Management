package com.hospitalwardmanagement.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
@Getter
public class ResourceNotExistForPatientException extends RuntimeException {
    private String resource;
    private Long fieldValue;


    public ResourceNotExistForPatientException(String resource, Long fieldValue) {
        super(String.format("%s for patient with id: %s not exist yet", resource, fieldValue));

        this.fieldValue = fieldValue;
        this.resource = resource;
    }
}
