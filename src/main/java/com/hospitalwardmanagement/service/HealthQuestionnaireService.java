package com.hospitalwardmanagement.service;

import com.hospitalwardmanagement.model.healthQuestionnaire.HealthQuestionnaire;
import com.hospitalwardmanagement.payload.HealthQuestionnaireDTO;

public interface HealthQuestionnaireService {
    HealthQuestionnaire addHealthQuestionnaireWithPatientId(HealthQuestionnaireDTO healthQuestionnaireDTO, Long patientId);
    HealthQuestionnaire updateHealthQuestionnaireByPatientId (HealthQuestionnaireDTO healthQuestionnaireDTO, Long patientId);
    void deleteHealthQuestionnaireByPatientId (Long patientId);
    HealthQuestionnaire getHealthQuestionnaireByPatientId(Long patientId);

}
