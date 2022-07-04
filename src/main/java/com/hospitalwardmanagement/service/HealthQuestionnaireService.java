package com.hospitalwardmanagement.service;

import com.hospitalwardmanagement.model.healthQuestionnaire.HealthQuestionnaire;

public interface HealthQuestionnaireService {
    HealthQuestionnaire addHealthQuestionnaireWithPatientId(HealthQuestionnaire healthQuestionnaire, Long patientId);
    HealthQuestionnaire updateHealthQuestionnaireById (Long id, HealthQuestionnaire healthQuestionnaire);
    HealthQuestionnaire updateHealthQuestionnaireByPatientId (Long patientId, HealthQuestionnaire healthQuestionnaire);
    void deleteHealthQuestionnaireById (Long id);
    void deleteHealthQuestionnaireByPatientId (Long patientId);
    HealthQuestionnaire getHealthQuestionnaireById(Long id);
    HealthQuestionnaire getHealthQuestionnaireByPatientId(Long patientId);

}
