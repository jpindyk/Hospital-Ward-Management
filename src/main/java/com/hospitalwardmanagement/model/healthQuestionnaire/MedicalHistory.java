package com.hospitalwardmanagement.model.healthQuestionnaire;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MedicalHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String disease;
    @ManyToOne
    @JoinColumn(name = "health_questionnaire_id")
    HealthQuestionnaire healthQuestionnaire;
}
