package com.hospitalwardmanagement.repository;

import com.hospitalwardmanagement.model.patient.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
