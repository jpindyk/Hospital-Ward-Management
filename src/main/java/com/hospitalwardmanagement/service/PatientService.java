package com.hospitalwardmanagement.service;

import com.hospitalwardmanagement.model.patient.Patient;

import java.util.List;

public interface PatientService {
    Patient addPatient (Patient patient);
    Patient updatePatientById (Long id, Patient patient);
    Patient getPatientById (Long id, Patient patient);
    Patient getPatientByPesel (String pesel);
    List<Patient> getAllPatients();
    List<Patient> getPatientsByHospitalRoom(Long hospitalRoomId);
    List<Patient> getPatientsByDoctor(Long doctorId);
    void deletePatient(Long id);


}
