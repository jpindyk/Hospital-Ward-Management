package com.hospitalwardmanagement.controller;

import com.hospitalwardmanagement.model.healthQuestionnaire.HealthQuestionnaire;
import com.hospitalwardmanagement.payload.HealthQuestionnaireDTO;
import com.hospitalwardmanagement.service.HealthQuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/hq")
public class HealthQuestionnaireController {

    @Autowired
    private HealthQuestionnaireService healthQuestionnaireService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') || hasRole('NURSE')")
    public ResponseEntity<HealthQuestionnaire> addHealthQuestionnaire (@Valid @RequestBody HealthQuestionnaireDTO healthQuestionnaireDTO,
                                                                       @RequestParam(name = "patient") Long patientId) {
        return new ResponseEntity<>(healthQuestionnaireService.addHealthQuestionnaireWithPatientId(healthQuestionnaireDTO, patientId), HttpStatus.CREATED);
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN') || hasRole('NURSE')")
    public ResponseEntity<HealthQuestionnaire> updateHealthQuestionnaire (@Valid @RequestBody HealthQuestionnaireDTO healthQuestionnaireDTO,
                                                                          @RequestParam(name = "patient") Long patientId) {
        return new ResponseEntity<>(healthQuestionnaireService.updateHealthQuestionnaireByPatientId(healthQuestionnaireDTO, patientId), HttpStatus.OK);
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ADMIN') || hasRole('NURSE')")
    public ResponseEntity<HealthQuestionnaire> deleteHealthQuestionnaireByPatientId (@RequestParam(name = "patient") Long patientId) {
        healthQuestionnaireService.deleteHealthQuestionnaireByPatientId(patientId);
        return new ResponseEntity<HealthQuestionnaire>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<HealthQuestionnaire> getHealthQuestionnaireByPatientId (@RequestParam(name = "patient") Long patientId) {
        return new ResponseEntity<HealthQuestionnaire>(healthQuestionnaireService.getHealthQuestionnaireByPatientId(patientId), HttpStatus.OK);
    }
}
