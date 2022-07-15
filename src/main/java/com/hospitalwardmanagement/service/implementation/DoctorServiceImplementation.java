package com.hospitalwardmanagement.service.implementation;

import com.hospitalwardmanagement.exceptions.ResourceNotFoundException;
import com.hospitalwardmanagement.model.doctor.Doctor;
import com.hospitalwardmanagement.repository.DoctorRepository;
import com.hospitalwardmanagement.repository.PatientRepository;
import com.hospitalwardmanagement.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImplementation implements DoctorService {

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    PatientRepository patientRepository;


    @Override
    public Doctor addDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor updateDoctorById(Long id, Doctor doctor) {
        Doctor existingDoctor = getDoctorById(id);

        existingDoctor.setSpecialization(doctor.getSpecialization() == null ? existingDoctor.getSpecialization() : doctor.getSpecialization());

        return doctorRepository.save(existingDoctor);
    }

    @Override
    public void deleteDoctorById(Long id) {
        patientRepository.findByDoctor(id).stream().forEach(p->p.setDoctor(null));
        Doctor doctorToDelete = getDoctorById(id);
        doctorRepository.delete(doctorToDelete);
    }

    @Override
    public Doctor getDoctorById(Long id) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Doctor", "id", id)
        );
        return doctor;
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public List<Doctor> getDoctorsBySpecialization(String specialization) {
        return doctorRepository.findDoctorBySpecialization(specialization);
    }


    @Override
    public List<Doctor> getDoctorByFirstNameAndLastName(String firstName, String lastName) {
        return doctorRepository.findDoctorByFirstNameAndLastName(firstName, lastName);
    }
}
