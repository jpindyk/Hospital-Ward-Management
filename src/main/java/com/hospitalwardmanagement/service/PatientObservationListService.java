package com.hospitalwardmanagement.service;

import com.hospitalwardmanagement.model.patientObservationList.PatientObservationList;

import java.util.List;

public interface PatientObservationListService {
    PatientObservationList addPatientObservationList (PatientObservationList patientObservationList, Long patientId);
    PatientObservationList updatePatientObservationListById (Long id, PatientObservationList patientObservationList);
    PatientObservationList getPatientObservationListById (Long id);
    List<PatientObservationList> getPatientObservationListsByPatientId (Long patientId);
    void deletePatientObservationListById (Long id);
    void deletePatientObservationListsByPatientId (Long patientId);


}
