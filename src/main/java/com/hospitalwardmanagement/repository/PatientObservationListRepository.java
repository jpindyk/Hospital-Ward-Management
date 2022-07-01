package com.hospitalwardmanagement.repository;

import com.hospitalwardmanagement.model.patientObservationList.PatientObservationList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientObservationListRepository extends JpaRepository<PatientObservationList, Long> {
}
