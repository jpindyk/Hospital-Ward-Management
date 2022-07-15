package com.hospitalwardmanagement.controller;

import com.hospitalwardmanagement.model.patient.Patient;
import com.hospitalwardmanagement.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {
    @Autowired
    PatientService patientService;

    @PostMapping
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient) {
        return new ResponseEntity<Patient>(patientService.addPatient(patient), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id,
                                                 @RequestBody Patient patient) {
        return new ResponseEntity<Patient>(patientService.updatePatientById(id, patient), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        return new ResponseEntity<Patient>(patientService.getPatientById(id), HttpStatus.OK);
    }

    @GetMapping("/pesel")
    public ResponseEntity<Patient> getPatientByPesel(@RequestParam String pesel) {
        return new ResponseEntity<Patient>(patientService.getPatientByPesel(pesel), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        return new ResponseEntity<List<Patient>>(patientService.getAllPatients(), HttpStatus.OK);
    }

    @GetMapping("/doctor")
    public ResponseEntity<List<Patient>> getPatientsByDoctor(@RequestParam Long doctorId) {
        return new ResponseEntity<List<Patient>>(patientService.getPatientsByDoctor(doctorId), HttpStatus.OK);
    }

    @GetMapping("/room")
    public ResponseEntity<List<Patient>> getPatientsByHospitalRoom(@RequestParam(name = "room") Long hospitalRoomId) {
        return new ResponseEntity<List<Patient>>(patientService.getPatientsByHospitalRoom(hospitalRoomId), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Patient> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Patient> setHospitalRoomForPatient(@PathVariable Long id,
                                                             @RequestParam(required = false, name = "room") Long hospitalRoomId,
                                                             @RequestParam(required = false, name = "doctor") Long doctorId) {
        if (hospitalRoomId != null) {
            patientService.setHospitalRoomForPatient(id, hospitalRoomId);
        }
        if (doctorId != null) {
            patientService.setDoctorForPatient(id, doctorId);
        }

        return new ResponseEntity<Patient>(HttpStatus.OK);
    }

}
