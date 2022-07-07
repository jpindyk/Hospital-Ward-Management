package com.hospitalwardmanagement.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
@Getter
public class HealthQuestionnaireNotExistForPatientException extends RuntimeException {

    private Long fieldValue;

    public HealthQuestionnaireNotExistForPatientException(Long fieldValue) {
        super(String.format("Health Questionnaire for patient with id: %s not exist yet", fieldValue));

        this.fieldValue = fieldValue;
    }
}
