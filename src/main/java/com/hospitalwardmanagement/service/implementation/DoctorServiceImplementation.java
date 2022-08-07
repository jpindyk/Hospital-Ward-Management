package com.hospitalwardmanagement.service.implementation;

import com.hospitalwardmanagement.exceptions.ResourceNotFoundException;
import com.hospitalwardmanagement.model.doctor.Doctor;
import com.hospitalwardmanagement.payload.DoctorDTO;
import com.hospitalwardmanagement.repository.DoctorRepository;
import com.hospitalwardmanagement.repository.PatientRepository;
import com.hospitalwardmanagement.service.DoctorService;
import com.hospitalwardmanagement.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImplementation implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper mapper;


    @Override
    public Doctor addDoctor(DoctorDTO doctorDTO) {
        Doctor doctor = mapToEntity(doctorDTO);
        doctor.getObjectAudit().setCreatedByUser(userService.getLoggedInUser().getEmail());
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor updateDoctorById(Long id, DoctorDTO doctorDTO) {
        Doctor existingDoctor = getDoctorById(id);
        existingDoctor.setFirstName(doctorDTO.getFirstName());
        existingDoctor.setLastName(doctorDTO.getLastName());
        existingDoctor.setSpecialization(doctorDTO.getSpecialization() == null ? existingDoctor.getSpecialization() : doctorDTO.getSpecialization());
        existingDoctor.getObjectAudit().setLastChangeByUser(userService.getLoggedInUser().getEmail());

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

    private Doctor mapToEntity (DoctorDTO postDto) {
        Doctor doctor = mapper.map(postDto, Doctor.class);
        return doctor;
    }
}
