package com.hospitalwardmanagement.service.implementation;

import com.hospitalwardmanagement.exceptions.HealthQuestionnaireExistsException;
import com.hospitalwardmanagement.exceptions.ResourceNotExistForPatientException;
import com.hospitalwardmanagement.exceptions.ResourceNotFoundException;
import com.hospitalwardmanagement.model.healthQuestionnaire.HealthQuestionnaire;
import com.hospitalwardmanagement.model.patient.Patient;
import com.hospitalwardmanagement.payload.HealthQuestionnaireDTO;
import com.hospitalwardmanagement.repository.HealthQuestionnaireRepository;
import com.hospitalwardmanagement.repository.PatientRepository;
import com.hospitalwardmanagement.service.HealthQuestionnaireService;
import com.hospitalwardmanagement.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HealthQuestionnaireServiceImplementation implements HealthQuestionnaireService {
    @Autowired
    private HealthQuestionnaireRepository questionnaireRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper mapper;

    @Override
    public HealthQuestionnaire addHealthQuestionnaireWithPatientId(HealthQuestionnaireDTO healthQuestionnaireDTO, Long patientId) {
        Patient patient = getPatient(patientId);

        if (patient.getHealthQuestionnaire() != null)
            throw new HealthQuestionnaireExistsException(patientId);

        HealthQuestionnaire newHealthQuestionnaire = mapToEntity(healthQuestionnaireDTO);
        newHealthQuestionnaire.setPatient(patient);
        newHealthQuestionnaire.getObjectAudit().setCreatedByUser(userService.getLoggedInUser());

        patient.setHealthQuestionnaire(newHealthQuestionnaire);

        return questionnaireRepository.save(newHealthQuestionnaire);
    }

    @Override
    public HealthQuestionnaire updateHealthQuestionnaireByPatientId(HealthQuestionnaireDTO healthQuestionnaireDTO, Long patientId) {
        Patient patient = getPatient(patientId);

        if(patient.getHealthQuestionnaire()==null)
            throw new ResourceNotExistForPatientException("Health Questionnaire", patientId);

        Long existingHealthQuestionnaireId = patient.getHealthQuestionnaire().getId();
        HealthQuestionnaire existingHealthQuestionnaire = getHealthQuestionnaireById(existingHealthQuestionnaireId);

        existingHealthQuestionnaire.setHeight(healthQuestionnaireDTO.getHeight() == null ? existingHealthQuestionnaire.getHeight() : healthQuestionnaireDTO.getHeight());
        existingHealthQuestionnaire.setWeight(healthQuestionnaireDTO.getWeight() == null ? existingHealthQuestionnaire.getWeight() : healthQuestionnaireDTO.getWeight());
        existingHealthQuestionnaire.setHistoryOfDiseases(healthQuestionnaireDTO.getHistoryOfDiseases());
        existingHealthQuestionnaire.getObjectAudit().setLastChangeByUser(userService.getLoggedInUser());
        return questionnaireRepository.save(existingHealthQuestionnaire);
    }

    @Override
    public void deleteHealthQuestionnaireByPatientId(Long patientId) {
        Patient patient = getPatient(patientId);
        if(patient.getHealthQuestionnaire()==null)
            throw new ResourceNotExistForPatientException("Health Questionnaire", patientId);


        HealthQuestionnaire healthQuestionnaireToDelete = getHealthQuestionnaireByPatientId(patientId);
        questionnaireRepository.delete(healthQuestionnaireToDelete);
        patient.setHealthQuestionnaire(null);
    }


    @Override
    public HealthQuestionnaire getHealthQuestionnaireByPatientId(Long patientId) {
        Patient patient = getPatient(patientId);
        if(patient.getHealthQuestionnaire()==null)
            throw new ResourceNotExistForPatientException("Health Questionnaire", patientId);

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

    private HealthQuestionnaire mapToEntity (HealthQuestionnaireDTO healthQuestionnaireDTO) {
        HealthQuestionnaire healthQuestionnaire = mapper.map(healthQuestionnaireDTO, HealthQuestionnaire.class);
        return healthQuestionnaire;
    }
}
