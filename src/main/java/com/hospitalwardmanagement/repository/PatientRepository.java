package com.hospitalwardmanagement.repository;

import com.hospitalwardmanagement.model.patient.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findByPesel (String pesel);
    List<Patient> findByHospitalRoom (Long hospitalRoomId);
    List<Patient> findByDoctor (Long doctorId);

}
