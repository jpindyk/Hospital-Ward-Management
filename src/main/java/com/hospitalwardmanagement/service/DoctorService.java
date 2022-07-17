package com.hospitalwardmanagement.service;

import com.hospitalwardmanagement.model.doctor.Doctor;
import com.hospitalwardmanagement.payload.DoctorDTO;

import java.util.List;

public interface DoctorService {

    Doctor addDoctor(DoctorDTO doctorDTO);
    Doctor updateDoctorById(Long id, DoctorDTO doctorDTO);
    void deleteDoctorById(Long id);
    Doctor getDoctorById (Long id);
    List<Doctor> getAllDoctors();
    List<Doctor> getDoctorsBySpecialization(String specialization);
    List<Doctor> getDoctorByFirstNameAndLastName(String firstName, String lastName);


}
