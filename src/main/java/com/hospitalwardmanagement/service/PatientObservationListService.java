package com.hospitalwardmanagement.service;

import com.hospitalwardmanagement.model.patientObservationList.PatientObservationList;
import com.hospitalwardmanagement.payload.PatientObservationListDTO;

import java.util.List;

public interface PatientObservationListService {
    PatientObservationList addPatientObservationList (PatientObservationListDTO patientObservationListDTO, Long patientId);
    PatientObservationList updatePatientObservationListById (Long id, PatientObservationListDTO patientObservationListDTO);
    PatientObservationList getPatientObservationListById (Long id);
    List<PatientObservationList> getPatientObservationListsByPatientId (Long patientId);
    void deletePatientObservationListById (Long id);
    void deletePatientObservationListsByPatientId (Long patientId);


}
