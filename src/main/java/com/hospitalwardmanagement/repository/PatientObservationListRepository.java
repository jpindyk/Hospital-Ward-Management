package com.hospitalwardmanagement.repository;

import com.hospitalwardmanagement.model.patientObservationList.PatientObservationLists;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientObservationListRepository extends JpaRepository<PatientObservationLists, Long> {
}
