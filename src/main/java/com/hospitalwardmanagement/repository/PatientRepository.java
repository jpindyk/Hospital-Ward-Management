package com.hospitalwardmanagement.repository;

import com.hospitalwardmanagement.model.patient.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findByPesel (String pesel);
    List<Patient> findByHospitalRoom (Long hospitalRoomId);
    List<Patient> findByDoctor (Long doctorId);

    @Query(value = "select count (p) from Patient p where p.hospitalRoom.id =:hospitalRoomId")
    Long patientsAmountInHospitalRoom (@Param("hospitalRoomId")Long hospitalRoomId);
    @Query(value = "select p from Patient p where p.doctor.id =:doctorId")
    List<Patient> patientsTreatByDoctor (@Param("doctorId")Long doctorId);
    @Query(value = "select p from Patient p where p.hospitalRoom.id =:hospitalRoomId")
    List<Patient> patientsInHospitalRoom (@Param("hospitalRoomId")Long hospitalRoomId);

}
