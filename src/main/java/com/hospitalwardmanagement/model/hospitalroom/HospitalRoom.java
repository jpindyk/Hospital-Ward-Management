package com.hospitalwardmanagement.model.hospitalroom;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hospitalwardmanagement.model.ObjectAudit;
import com.hospitalwardmanagement.model.patient.Patient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class HospitalRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    Byte capacity;
    @OneToMany(mappedBy = "hospitalRoom")
    List<Patient> patients;

    @JsonIgnore
    ObjectAudit objectAudit = new ObjectAudit();
}
