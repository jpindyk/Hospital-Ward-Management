package com.hospitalwardmanagement.model.healthQuestionnaire;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class MedicalHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String disease;
    @ManyToOne
    @JoinColumn(name = "health_questionnaire_id")
    @JsonBackReference
    HealthQuestionnaire healthQuestionnaire;
}
