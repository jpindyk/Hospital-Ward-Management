package com.hospitalwardmanagement.repository;

import com.hospitalwardmanagement.model.doctor.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    List<Doctor> findDoctorBySpecialization (String specialization);
    List<Doctor> findDoctorByFirstNameAndLastName (String firstName, String lastName);
}
