package com.hospitalwardmanagement.model.healthQuestionnaire;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hospitalwardmanagement.model.ObjectAudit;
import com.hospitalwardmanagement.model.doctor.Doctor;
import com.hospitalwardmanagement.model.patient.Patient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class HealthQuestionnaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long height;
    Long weight;
    @OneToMany(mappedBy = "healthQuestionnaire")
    Set<MedicalHistory> medicalHistories;
    @OneToOne
    @JoinColumn(name = "patient_id")
    Patient patient;

    @JsonIgnore
    ObjectAudit objectAudit = new ObjectAudit();
}
