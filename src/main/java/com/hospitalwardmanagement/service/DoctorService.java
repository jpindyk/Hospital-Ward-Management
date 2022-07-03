package com.hospitalwardmanagement.service;

import com.hospitalwardmanagement.model.doctor.Doctor;

import java.util.List;

public interface DoctorService {

    Doctor addDoctor(Doctor doctor);
    Doctor updateDoctorById(Long id, Doctor doctor);
    void deleteDoctorById(Long id);
    Doctor getDoctorById (Long id);
    List<Doctor> getAllDoctors();
    List<Doctor> getDoctorsBySpecialization(String specialization);
    List<Doctor> getDoctorByFirstNameAndLastName(String firstName, String lastName);


}
