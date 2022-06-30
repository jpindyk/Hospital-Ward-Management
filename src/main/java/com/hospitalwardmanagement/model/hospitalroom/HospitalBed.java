package com.hospitalwardmanagement.model.hospitalroom;

import com.hospitalwardmanagement.model.patient.Patient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "hospital_beds")
public class HospitalBed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    HospitalRoom hospitalRoom;
    public String getHospitalBedSymbol() {
        return hospitalRoom.name + id;
    }
    boolean isEmpty;

    @OneToOne
    Patient patient;
}
