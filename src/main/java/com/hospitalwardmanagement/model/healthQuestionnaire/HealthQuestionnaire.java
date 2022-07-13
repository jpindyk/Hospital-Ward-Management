package com.hospitalwardmanagement.model.healthQuestionnaire;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.hospitalwardmanagement.model.ObjectAudit;
import com.hospitalwardmanagement.model.doctor.Doctor;
import com.hospitalwardmanagement.model.patient.Patient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class HealthQuestionnaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "patient_id")
    @JsonBackReference
    Patient patient;

    Long height;
    Long weight;

    @OneToMany(mappedBy = "healthQuestionnaire", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference
    List<MedicalHistory> medicalHistories;

    @JsonIgnore
    ObjectAudit objectAudit = new ObjectAudit();
}
