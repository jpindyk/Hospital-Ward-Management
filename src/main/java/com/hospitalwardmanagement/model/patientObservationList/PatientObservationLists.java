package com.hospitalwardmanagement.model.patientObservationList;

import com.hospitalwardmanagement.model.patient.Patient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PatientObservationLists {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String bloodPressure;
    String bodyTemperature;
    String shortSummary;
    @ManyToOne
    @JoinColumn(name = "patient_id")
    Patient patient;

}
