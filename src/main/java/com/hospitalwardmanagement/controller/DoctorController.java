package com.hospitalwardmanagement.controller;

import com.hospitalwardmanagement.model.doctor.Doctor;
import com.hospitalwardmanagement.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
    @Autowired
    DoctorService doctorService;

    @GetMapping("/{doctorId}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable Long doctorId) {
        return new ResponseEntity<>(doctorService.getDoctorById(doctorId), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        return new ResponseEntity<>(doctorService.getAllDoctors(), HttpStatus.OK);
    }

    @GetMapping("/spec")
    public ResponseEntity<List<Doctor>> getDoctorBySpecialization(
                                                @RequestParam(required = true, name = "spec") String specialization) {

        return new ResponseEntity<>(doctorService.getDoctorsBySpecialization(specialization), HttpStatus.OK);
    }

    @GetMapping("/name")
    public ResponseEntity<List<Doctor>> getDoctorByFirstNameAndLastName(
                                                @RequestParam(required = true, name = "first") String firstName,
                                                @RequestParam(required = true, name = "last") String lastName) {

        return new ResponseEntity<>(doctorService.getDoctorByFirstNameAndLastName(firstName, lastName), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Doctor> addDcotor(@RequestBody Doctor doctor) {
        return new ResponseEntity<>(doctorService.addDoctor(doctor), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable Long id, @RequestBody Doctor doctor) {
        return new ResponseEntity<>(doctorService.updateDoctorById(id, doctor), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Doctor> deleteDoctorById(@PathVariable Long id) {
        doctorService.deleteDoctorById(id);
        return new ResponseEntity<Doctor>(HttpStatus.OK);
    }



}