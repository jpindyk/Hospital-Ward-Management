package com.hospitalwardmanagement.controller;

import com.hospitalwardmanagement.model.healthQuestionnaire.HealthQuestionnaire;
import com.hospitalwardmanagement.service.HealthQuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients/{patientId}/hq")
public class HealthQuestionnaireController {

    @Autowired
    HealthQuestionnaireService healthQuestionnaireService;

    @PostMapping
    public ResponseEntity<HealthQuestionnaire> addHealthQuestionnaire (@RequestBody HealthQuestionnaire healthQuestionnaire,
                                                                       @PathVariable Long patientId) {
        return new ResponseEntity<>(healthQuestionnaireService.addHealthQuestionnaireWithPatientId(healthQuestionnaire, patientId), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<HealthQuestionnaire> updateHealthQuestionnaire (@RequestBody HealthQuestionnaire healthQuestionnaire,
                                                                          @PathVariable Long patientId) {
        return new ResponseEntity<>(healthQuestionnaireService.updateHealthQuestionnaireByPatientId(healthQuestionnaire, patientId), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<HealthQuestionnaire> deleteHealthQuestionnaireByPatientId (@PathVariable Long patientId) {
        healthQuestionnaireService.deleteHealthQuestionnaireByPatientId(patientId);
        return new ResponseEntity<HealthQuestionnaire>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<HealthQuestionnaire> getHealthQuestionnaireByPatientId (@PathVariable Long patientId) {
        return new ResponseEntity<HealthQuestionnaire>(healthQuestionnaireService.getHealthQuestionnaireByPatientId(patientId), HttpStatus.OK);
    }
}
