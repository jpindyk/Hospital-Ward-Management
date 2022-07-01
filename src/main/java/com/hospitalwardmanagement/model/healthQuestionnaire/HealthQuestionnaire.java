package com.hospitalwardmanagement.model.healthQuestionnaire;

import com.hospitalwardmanagement.model.doctor.Doctor;
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
    Set<MedicalHistory> getMedicalHistory;

}
