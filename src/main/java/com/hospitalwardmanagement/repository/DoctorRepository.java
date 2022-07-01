package com.hospitalwardmanagement.repository;

import com.hospitalwardmanagement.model.doctor.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
