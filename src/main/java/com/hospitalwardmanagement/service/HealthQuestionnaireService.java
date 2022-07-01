package com.hospitalwardmanagement.service;

import com.hospitalwardmanagement.model.healthQuestionnaire.HealthQuestionnaire;

public interface HealthQuestionnaireService {
    HealthQuestionnaire addHealthQuestionnaireWithPatientId(HealthQuestionnaire healthQuestionnaire, Long patientId);
    HealthQuestionnaire addHealthQuestionnaireWithPatientPesel(HealthQuestionnaire healthQuestionnaire, String pesel);
    HealthQuestionnaire addHealthQuestionnaireWithPatientFirstNameAndLastName(
            HealthQuestionnaire healthQuestionnaire, String firstName, String lastName
    );
    HealthQuestionnaire updateHealthQuestionnaireById (Long id, HealthQuestionnaire healthQuestionnaire);
    void deleteHealthQuestionnaireById (Long id);
    void deleteHealthQuestionnaireByPatientId (Long patientId);
    HealthQuestionnaire getHealthQuestionnaireById(Long id);
    HealthQuestionnaire getHealthQuestionnaireByPatientId(Long id);
    HealthQuestionnaire getHealthQuestionnaireByPatientPesel(String pesel);
    HealthQuestionnaire getHealthQuestionnaireByPatientFirstNameAndLastName(String firstName, String lastName);
}
