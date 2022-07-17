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
import javax.validation.constraints.Size;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class PatientObservationList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String bloodPressure;
    private String bodyTemperature;
    @Size(min=25, message = "Summary should have at least 25 characters")
    private String shortSummary;
    @ManyToOne
    @JoinColumn(name = "patient_id")
    @JsonBackReference
    private Patient patient;

    @JsonIgnore
    private ObjectAudit objectAudit = new ObjectAudit();


}
