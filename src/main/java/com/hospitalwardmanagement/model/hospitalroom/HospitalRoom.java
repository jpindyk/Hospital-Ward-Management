package com.hospitalwardmanagement.model.hospitalroom;

import com.hospitalwardmanagement.model.patient.Patient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "hospital_rooms")
public class HospitalRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    Byte capacity;
    Byte amountOfPatients;
    boolean isRoomFull;

    @OneToMany
    List<HospitalBed> hospitalBeds;
    @OneToMany
    List<Patient> patients;


}
