package com.hospitalwardmanagement.service;

import com.hospitalwardmanagement.model.patientObservationList.PatientObservationList;

import java.util.List;

public interface PatientObservationListService {
    PatientObservationList addPatientObservationList (PatientObservationList patientObservationList);
    PatientObservationList updatePatientObservationListById (Long id, PatientObservationList patientObservationList);
    List<PatientObservationList> getPatientObservationListById (Long id);
    List<PatientObservationList> getPatientObservationListByPatientId (Long patientId);
    List<PatientObservationList> getPatientObservationListByPatientPesel (String pesel);
    List<PatientObservationList> getPatientObservationListByPatientFirstNameAndLastName(String firstName, String lastName);
    void deletePatientObservationListById (Long id);
    void deletePatientObservationListByPatientId (Long patientId);


}
