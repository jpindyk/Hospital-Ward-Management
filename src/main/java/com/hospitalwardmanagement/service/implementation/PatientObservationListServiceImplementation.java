package com.hospitalwardmanagement.service.implementation;

import com.hospitalwardmanagement.exceptions.ResourceNotExistForPatientException;
import com.hospitalwardmanagement.exceptions.ResourceNotFoundException;
import com.hospitalwardmanagement.model.patient.Patient;
import com.hospitalwardmanagement.model.patientObservationList.PatientObservationList;
import com.hospitalwardmanagement.repository.PatientObservationListRepository;
import com.hospitalwardmanagement.repository.PatientRepository;
import com.hospitalwardmanagement.service.PatientObservationListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientObservationListServiceImplementation implements PatientObservationListService {

    @Autowired
    PatientObservationListRepository repository;

    @Autowired
    PatientRepository patientRepository;

    @Override
    public PatientObservationList addPatientObservationList(PatientObservationList patientObservationList, Long patientId) {
        Patient patient = getPatient(patientId);
        patient.getPatientObservationLists().add(patientObservationList);
        patientObservationList.setPatient(patient);
        return repository.save(patientObservationList);
    }

    @Override
    public PatientObservationList updatePatientObservationListById(Long id, PatientObservationList patientObservationList) {
        PatientObservationList existingPatientObservationList = getPatientObservationListById(id);

        existingPatientObservationList.setBloodPressure(
                patientObservationList.getBloodPressure() == null ? existingPatientObservationList.getBloodPressure() : patientObservationList.getBloodPressure()
                );
        existingPatientObservationList.setBodyTemperature(
                patientObservationList.getBodyTemperature() == null ? existingPatientObservationList.getBodyTemperature() : patientObservationList.getBodyTemperature()
        );
        existingPatientObservationList.setShortSummary(
                patientObservationList.getShortSummary()==null ? existingPatientObservationList.getShortSummary() : patientObservationList.getShortSummary()
        );

        return repository.save(existingPatientObservationList);

    }


    @Override
    public PatientObservationList getPatientObservationListById(Long id) {
        return repository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Patient Observation List", "id", id)
        );
    }

    @Override
    public List<PatientObservationList> getPatientObservationListsByPatientId(Long patientId) {
        return getPatient(patientId).getPatientObservationLists();
    }

    @Override
    public void deletePatientObservationListById(Long id) {
        PatientObservationList patientObservationListToDelete = getPatientObservationListById(id);
        repository.delete(patientObservationListToDelete);
    }

    @Override
    public void deletePatientObservationListsByPatientId(Long patientId) {
        Patient patient = getPatient(patientId);
        List<PatientObservationList> patientObservationListsToDelete = patient.getPatientObservationLists();
        repository.deleteAll(patientObservationListsToDelete);
    }

    public Patient getPatient(Long patientId) {
        return patientRepository.findById(patientId).orElseThrow(
                () -> new ResourceNotFoundException("Patient", "id", patientId)
        );
    }
}
