package com.hospitalwardmanagement.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class HealthQuestionnaireDTO {
    private Long height;
    private Long weight;
    @NotBlank
    private String historyOfDiseases;
}
