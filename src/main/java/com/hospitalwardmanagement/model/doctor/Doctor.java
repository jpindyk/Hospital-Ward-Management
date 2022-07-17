package com.hospitalwardmanagement.model.doctor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hospitalwardmanagement.model.ObjectAudit;
import com.hospitalwardmanagement.model.patient.Patient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Doctor{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Doctor's first name should not be null or empty")
    private String firstName;
    @NotBlank(message = "Doctor's last name should not be null or empty")
    private String lastName;
    private String specialization;
    @OneToMany (mappedBy = "doctor")
    @JsonIgnore
    private Set<Patient> patients;

    @JsonIgnore
    private ObjectAudit objectAudit = new ObjectAudit();

}
