package com.hospitalwardmanagement.service.implementation;

import com.hospitalwardmanagement.exceptions.HospitalRoomFullException;
import com.hospitalwardmanagement.exceptions.ResourceNotFoundException;
import com.hospitalwardmanagement.model.doctor.Doctor;
import com.hospitalwardmanagement.model.hospitalroom.HospitalRoom;
import com.hospitalwardmanagement.model.patient.Patient;
import com.hospitalwardmanagement.payload.PatientDTO;
import com.hospitalwardmanagement.repository.DoctorRepository;
import com.hospitalwardmanagement.repository.HospitalRoomRepository;
import com.hospitalwardmanagement.repository.PatientRepository;
import com.hospitalwardmanagement.service.PatientService;
import com.hospitalwardmanagement.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImplementation implements PatientService {
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private HospitalRoomRepository hospitalRoomRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserService userService;

    @Override
    public Patient addPatient(PatientDTO patientDTO) {
        Patient patient = mapToEntity(patientDTO);
        patient.getObjectAudit().setCreatedByUser(userService.getLoggedInUser());
        return patientRepository.save(patient);
    }

    @Override
    public Patient updatePatientById(Long id, PatientDTO patientDTO) {
        Patient existingPatient = getPatientById(id);

        existingPatient.setFirstName(patientDTO.getFirstName() == null ? existingPatient.getFirstName() : patientDTO.getFirstName());
        existingPatient.setLastName(patientDTO.getLastName() == null ? existingPatient.getLastName() : patientDTO.getLastName());
        existingPatient.setHomeAddress(patientDTO.getHomeAddress() == null ? existingPatient.getHomeAddress() : patientDTO.getHomeAddress());
        existingPatient.setCorrespondenceAddress(patientDTO.getCorrespondenceAddress() == null ? existingPatient.getCorrespondenceAddress() : patientDTO.getCorrespondenceAddress());
        existingPatient.setPesel(patientDTO.getPesel() == null ? existingPatient.getPesel() : patientDTO.getPesel());
        existingPatient.setPhoneNumber(patientDTO.getPhoneNumber() == null ? existingPatient.getPhoneNumber() : patientDTO.getPhoneNumber());
        existingPatient.setEmail(patientDTO.getEmail() == null ? existingPatient.getEmail() : patientDTO.getEmail());
        existingPatient.setJob(patientDTO.getJob() == null ? existingPatient.getJob() : patientDTO.getJob());
        existingPatient.setMaritalStatus(patientDTO.getMaritalStatus() == null ? existingPatient.getMaritalStatus() : patientDTO.getMaritalStatus());
        existingPatient.setContactPerson(patientDTO.getContactPerson() == null ? existingPatient.getContactPerson() : patientDTO.getContactPerson());
        existingPatient.getObjectAudit().setLastChangeByUser(userService.getLoggedInUser());

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
        if (hospitalRoom.getCapacity() <= patientRepository.patientsAmountInHospitalRoom(hospitalRoomId))
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

    private Patient mapToEntity (PatientDTO patientDTO) {
        Patient patient = mapper.map(patientDTO, Patient.class);
        return patient;
    }
}
