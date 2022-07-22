package com.hospitalwardmanagement.controller;

import com.hospitalwardmanagement.model.patient.Patient;
import com.hospitalwardmanagement.payload.PatientDTO;
import com.hospitalwardmanagement.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') || hasRole('REGISTRAR')")
    public ResponseEntity<Patient> addPatient(@Valid @RequestBody PatientDTO patientDTO) {
        return new ResponseEntity<Patient>(patientService.addPatient(patientDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') || hasRole('REGISTRAR')")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id,
                                                 @Valid @RequestBody PatientDTO patientDTO) {
        return new ResponseEntity<Patient>(patientService.updatePatientById(id, patientDTO), HttpStatus.OK);
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
    @PreAuthorize("hasRole('ADMIN') || hasRole('NURSE_WARD')")
    public ResponseEntity<Patient> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') || hasRole('NURSE_WARD')")
    public ResponseEntity<Patient> setHospitalRoomOrDoctorForPatient(@PathVariable Long id,
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
