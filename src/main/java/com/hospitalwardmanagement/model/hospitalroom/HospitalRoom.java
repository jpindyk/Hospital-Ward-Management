package com.hospitalwardmanagement.model.hospitalroom;

import com.hospitalwardmanagement.model.patient.Patient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class HospitalRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    Byte capacity;
    Byte amountOfPatients;
    boolean isRoomFull;
    @OneToMany(mappedBy = "hospitalRoom")
    List<Patient> patients;

}
