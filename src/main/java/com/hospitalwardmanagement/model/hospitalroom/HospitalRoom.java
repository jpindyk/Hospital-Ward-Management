package com.hospitalwardmanagement.model.hospitalroom;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hospitalwardmanagement.model.ObjectAudit;
import com.hospitalwardmanagement.model.patient.Patient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

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
    @Size(min = 3, max = 10, message = "Hospital Room should have at least 3 beds and max 10 beds")
    Integer capacity;
    @OneToMany(mappedBy = "hospitalRoom")
    @JsonIgnore
    Set<Patient> patients;

    @JsonIgnore
    ObjectAudit objectAudit = new ObjectAudit();
}
