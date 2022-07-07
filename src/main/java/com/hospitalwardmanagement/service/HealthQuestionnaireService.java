package com.hospitalwardmanagement.service;

import com.hospitalwardmanagement.model.healthQuestionnaire.HealthQuestionnaire;

public interface HealthQuestionnaireService {
    HealthQuestionnaire addHealthQuestionnaireWithPatientId(HealthQuestionnaire healthQuestionnaire, Long patientId);
    HealthQuestionnaire updateHealthQuestionnaireByPatientId (HealthQuestionnaire healthQuestionnaire, Long patientId);
    void deleteHealthQuestionnaireByPatientId (Long patientId);
    HealthQuestionnaire getHealthQuestionnaireByPatientId(Long patientId);

}
