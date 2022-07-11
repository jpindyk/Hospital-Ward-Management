package com.hospitalwardmanagement.controller;

import com.hospitalwardmanagement.model.patientObservationList.PatientObservationList;
import com.hospitalwardmanagement.service.PatientObservationListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/observations")
public class PatientObservationListController {

    @Autowired
    PatientObservationListService service;

    @PostMapping("/patient/{patientId}")
    public ResponseEntity<PatientObservationList> addPatientObservationList (@RequestBody PatientObservationList patientObservationList,
                                                                             @PathVariable Long patientId) {
        return new ResponseEntity<PatientObservationList>(service.addPatientObservationList(patientObservationList, patientId), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientObservationList> updatePatientObservationList (@RequestBody PatientObservationList patientObservationList,
                                                                             @PathVariable Long id) {
        return new ResponseEntity<PatientObservationList>(service.updatePatientObservationListById(id, patientObservationList), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientObservationList> getPatientObservationList (@PathVariable  Long id) {
        return new ResponseEntity<PatientObservationList>(service.getPatientObservationListById(id), HttpStatus.OK);
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<PatientObservationList>> getPatientObservationListsByPatientId (@PathVariable Long patientId) {
        return new ResponseEntity<List<PatientObservationList>>(service.getPatientObservationListsByPatientId(patientId), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PatientObservationList> deletePatientObservationList (@PathVariable Long id) {
        service.deletePatientObservationListById(id);
        return new ResponseEntity<PatientObservationList>(HttpStatus.OK);
    }

    @DeleteMapping("/patient/{patientId}")
    public ResponseEntity<PatientObservationList> deletePatientObservationListsByPatientId (@PathVariable Long patientId) {
        service.deletePatientObservationListsByPatientId(patientId);
        return new ResponseEntity<PatientObservationList>(HttpStatus.OK);
    }


}
