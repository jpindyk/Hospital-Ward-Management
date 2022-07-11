package com.hospitalwardmanagement.service.implementation;

import com.hospitalwardmanagement.exceptions.HospitalRoomFullException;
import com.hospitalwardmanagement.exceptions.ResourceNotFoundException;
import com.hospitalwardmanagement.model.doctor.Doctor;
import com.hospitalwardmanagement.model.hospitalroom.HospitalRoom;
import com.hospitalwardmanagement.model.patient.Patient;
import com.hospitalwardmanagement.repository.DoctorRepository;
import com.hospitalwardmanagement.repository.HospitalRoomRepository;
import com.hospitalwardmanagement.repository.PatientRepository;
import com.hospitalwardmanagement.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImplementation implements PatientService {
    @Autowired
    PatientRepository patientRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    HospitalRoomRepository hospitalRoomRepository;

    @Override
    public Patient addPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Patient updatePatientById(Long id, Patient patient) {
        Patient existingPatient = getPatientById(id);

        existingPatient.setFirstName(patient.getFirstName() == null ? existingPatient.getFirstName() : patient.getFirstName());
        existingPatient.setLastName(patient.getLastName() == null ? existingPatient.getLastName() : patient.getLastName());
        existingPatient.setHomeAddress(patient.getHomeAddress() == null ? existingPatient.getHomeAddress() : patient.getHomeAddress());
        existingPatient.setCorrespondenceAddress(patient.getCorrespondenceAddress() == null ? existingPatient.getCorrespondenceAddress() : patient.getCorrespondenceAddress());
        existingPatient.setPesel(patient.getPesel() == null ? existingPatient.getPesel() : patient.getPesel());
        existingPatient.setPhoneNumber(patient.getPhoneNumber() == null ? existingPatient.getPhoneNumber() : patient.getPhoneNumber());
        existingPatient.setEmail(patient.getEmail() == null ? existingPatient.getEmail() : patient.getEmail());
        existingPatient.setJob(patient.getJob() == null ? existingPatient.getJob() : patient.getJob());
        existingPatient.setMaritalStatus(patient.getMaritalStatus() == null ? existingPatient.getMaritalStatus() : patient.getMaritalStatus());
        existingPatient.setContactPerson(patient.getContactPerson() == null ? existingPatient.getContactPerson() : patient.getContactPerson());

        return patientRepository.save(existingPatient);
    }

    @Override
    public Patient getPatientById(Long id) {
        return patientRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Patient", "id", id)
        );
    }

    @Override
    public Patient getPatientByPesel(String pesel) {
        return patientRepository.findByPesel(pesel).orElseThrow(
                () -> new ResourceNotFoundException("Patient", "pesel", pesel)
        );
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public List<Patient> getPatientsByHospitalRoom(Long hospitalRoomId) {
        return patientRepository.findByHospitalRoom(hospitalRoomId);
    }

    @Override
    public List<Patient> getPatientsByDoctor(Long doctorId) {
        return patientRepository.findByDoctor(doctorId);
    }

    @Override
    public void deletePatient(Long id) {
        Patient patientToDelete = getPatientById(id);
        patientRepository.delete(patientToDelete);
    }

    @Override
    public Patient setHospitalRoomForPatient(Long patientId, Long hospitalRoomId) {
        Patient patient = getPatientById(patientId);
        HospitalRoom hospitalRoom = hospitalRoomRepository.findById(hospitalRoomId).orElseThrow(
                () -> new ResourceNotFoundException("HospitalRoom", "id", hospitalRoomId)
        );
        if (hospitalRoom.getCapacity() <= hospitalRoom.getPatients().size())
            throw new HospitalRoomFullException(hospitalRoomId);

        patient.setHospitalRoom(hospitalRoom);
        hospitalRoom.getPatients().add(patient);

        return patientRepository.save(patient);

    }

    @Override
    public Patient setDoctorForPatient(Long patientId, Long doctorId) {
        Patient patient = getPatientById(patientId);
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(
                () -> new ResourceNotFoundException("Doctor", "id", doctorId)
        );

        patient.setDoctor(doctor);
        doctor.getPatients().add(patient);
        return patientRepository.save(patient);
    }
}
