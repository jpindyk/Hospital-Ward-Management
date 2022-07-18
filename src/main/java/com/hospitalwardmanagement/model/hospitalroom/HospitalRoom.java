package com.hospitalwardmanagement.model.hospitalroom;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hospitalwardmanagement.model.ObjectAudit;
import com.hospitalwardmanagement.model.patient.Patient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(
        uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})}
)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class HospitalRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer capacity;
    @OneToMany(mappedBy = "hospitalRoom")
    @JsonIgnore
    private Set<Patient> patients;

    @JsonIgnore
    private ObjectAudit objectAudit = new ObjectAudit();
}
