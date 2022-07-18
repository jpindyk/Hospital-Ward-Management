package com.hospitalwardmanagement.service.implementation;

import com.hospitalwardmanagement.exceptions.ResourceNotFoundException;
import com.hospitalwardmanagement.model.patient.Patient;
import com.hospitalwardmanagement.model.patientObservationList.PatientObservationList;
import com.hospitalwardmanagement.payload.PatientObservationListDTO;
import com.hospitalwardmanagement.repository.PatientObservationListRepository;
import com.hospitalwardmanagement.repository.PatientRepository;
import com.hospitalwardmanagement.service.PatientObservationListService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientObservationListServiceImplementation implements PatientObservationListService {

    @Autowired
    PatientObservationListRepository repository;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public PatientObservationList addPatientObservationList(PatientObservationListDTO patientObservationListDTO, Long patientId) {
        Patient patient = getPatient(patientId);
        PatientObservationList patientObservationList = mapToEntity(patientObservationListDTO);

        patient.getPatientObservationLists().add(patientObservationList);
        patientObservationList.setPatient(patient);
        return repository.save(patientObservationList);
    }

    @Override
    public PatientObservationList updatePatientObservationListById(Long id, PatientObservationListDTO patientObservationListDTO) {
        PatientObservationList existingPatientObservationList = getPatientObservationListById(id);

        existingPatientObservationList.setBloodPressure(
                patientObservationListDTO.getBloodPressure() == null ? existingPatientObservationList.getBloodPressure() : patientObservationListDTO.getBloodPressure()
                );
        existingPatientObservationList.setBodyTemperature(
                patientObservationListDTO.getBodyTemperature() == null ? existingPatientObservationList.getBodyTemperature() : patientObservationListDTO.getBodyTemperature()
        );
        existingPatientObservationList.setShortSummary(
                patientObservationListDTO.getShortSummary()==null ? existingPatientObservationList.getShortSummary() : patientObservationListDTO.getShortSummary()
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

    private Patient getPatient(Long patientId) {
        return patientRepository.findById(patientId).orElseThrow(
                () -> new ResourceNotFoundException("Patient", "id", patientId)
        );
    }

    private PatientObservationList mapToEntity (PatientObservationListDTO patientObservationListDTO) {
        PatientObservationList patientObservationList = mapper.map(patientObservationListDTO, PatientObservationList.class);
        return patientObservationList;
    }
}
