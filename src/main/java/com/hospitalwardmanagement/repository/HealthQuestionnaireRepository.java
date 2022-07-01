package com.hospitalwardmanagement.repository;

import com.hospitalwardmanagement.model.healthQuestionnaire.HealthQuestionnaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HealthQuestionnaireRepository extends JpaRepository<HealthQuestionnaire, Long> {
}
