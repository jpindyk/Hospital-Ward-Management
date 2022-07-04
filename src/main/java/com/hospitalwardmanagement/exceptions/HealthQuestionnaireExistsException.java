package com.hospitalwardmanagement.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
@Getter
public class HealthQuestionnaireExistsException extends RuntimeException {

    private Long fieldValue;

    public HealthQuestionnaireExistsException(Long fieldValue) {
        super(String.format("Health Questionnaire for patient with id: %s already exist", fieldValue));

        this.fieldValue = fieldValue;
    }
}
