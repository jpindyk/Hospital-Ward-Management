package com.hospitalwardmanagement.model.patientObservationList;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hospitalwardmanagement.model.ObjectAudit;
import com.hospitalwardmanagement.model.patient.Patient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class PatientObservationList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String bloodPressure;
    String bodyTemperature;
    String shortSummary;
    @ManyToOne
    @JoinColumn(name = "patient_id")
    @JsonBackReference
    Patient patient;

    @JsonIgnore
    ObjectAudit objectAudit = new ObjectAudit();


}
