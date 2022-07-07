package com.hospitalwardmanagement.service.implementation;

import com.hospitalwardmanagement.exceptions.HealthQuestionnaireExistsException;
import com.hospitalwardmanagement.exceptions.HealthQuestionnaireNotExistForPatientException;
import com.hospitalwardmanagement.exceptions.ResourceNotFoundException;
import com.hospitalwardmanagement.model.healthQuestionnaire.HealthQuestionnaire;
import com.hospitalwardmanagement.model.patient.Patient;
import com.hospitalwardmanagement.repository.HealthQuestionnaireRepository;
import com.hospitalwardmanagement.repository.PatientRepository;
import com.hospitalwardmanagement.service.HealthQuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HealthQuestionnaireServiceImplementation implements HealthQuestionnaireService {
    @Autowired
    HealthQuestionnaireRepository questionnaireRepository;

    @Autowired
    PatientRepository patientRepository;

    @Override
    public HealthQuestionnaire addHealthQuestionnaireWithPatientId(HealthQuestionnaire healthQuestionnaire, Long patientId) {
        Patient patient = getPatient(patientId);

        if (patient.getHealthQuestionnaire() != null)
            throw new HealthQuestionnaireExistsException(patientId);

        HealthQuestionnaire newHealthQuestionnaire = healthQuestionnaire;
        newHealthQuestionnaire.setPatient(patient);

        newHealthQuestionnaire.getMedicalHistories().
                forEach(medicalHistory -> medicalHistory
                        .setHealthQuestionnaire(healthQuestionnaire));

        patient.setHealthQuestionnaire(newHealthQuestionnaire);

        return questionnaireRepository.save(newHealthQuestionnaire);
    }

    @Override
    public HealthQuestionnaire updateHealthQuestionnaireByPatientId(HealthQuestionnaire healthQuestionnaire, Long patientId) {
        Patient patient = getPatient(patientId);

        if(patient.getHealthQuestionnaire()==null)
            throw new HealthQuestionnaireNotExistForPatientException(patientId);

        Long existingHealthQuestionnaireId = patient.getHealthQuestionnaire().getId();
        HealthQuestionnaire existingHealthQuestionnaire = getHealthQuestionnaireById(existingHealthQuestionnaireId);

        existingHealthQuestionnaire.setHeight(healthQuestionnaire.getHeight() == null ? existingHealthQuestionnaire.getHeight() : healthQuestionnaire.getHeight());
        existingHealthQuestionnaire.setWeight(healthQuestionnaire.getWeight() == null ? existingHealthQuestionnaire.getWeight() : healthQuestionnaire.getWeight());
        existingHealthQuestionnaire.setMedicalHistories(healthQuestionnaire.getMedicalHistories() == null ? existingHealthQuestionnaire.getMedicalHistories() : healthQuestionnaire.getMedicalHistories());

        return questionnaireRepository.save(existingHealthQuestionnaire);
    }

    @Override
    public void deleteHealthQuestionnaireByPatientId(Long patientId) {
        Patient patient = getPatient(patientId);
        if(patient.getHealthQuestionnaire()==null)
            throw new HealthQuestionnaireNotExistForPatientException(patientId);

        patient.setHealthQuestionnaire(null);

        HealthQuestionnaire healthQuestionnaireToDelete = getHealthQuestionnaireByPatientId(patientId);
        questionnaireRepository.delete(healthQuestionnaireToDelete);
    }


    @Override
    public HealthQuestionnaire getHealthQuestionnaireByPatientId(Long patientId) {
        Patient patient = getPatient(patientId);
        if(patient.getHealthQuestionnaire()==null)
            throw new HealthQuestionnaireNotExistForPatientException(patientId);

        Long existingHealthQuestionnaireId = patient.getHealthQuestionnaire().getId();
        return getHealthQuestionnaireById(existingHealthQuestionnaireId);
    }

    public HealthQuestionnaire getHealthQuestionnaireById(Long id) {
        return questionnaireRepository.findById(id).get();
    }


    public Patient getPatient(Long patientId) {
        return patientRepository.findById(patientId).orElseThrow(
                () -> new ResourceNotFoundException("Patient", "id", patientId)
        );
    }

}