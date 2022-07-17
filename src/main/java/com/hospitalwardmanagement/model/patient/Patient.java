package com.hospitalwardmanagement.model.patient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.hospitalwardmanagement.model.ObjectAudit;
import com.hospitalwardmanagement.model.doctor.Doctor;
import com.hospitalwardmanagement.model.healthQuestionnaire.HealthQuestionnaire;
import com.hospitalwardmanagement.model.hospitalroom.HospitalRoom;
import com.hospitalwardmanagement.model.patientObservationList.PatientObservationList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.pl.PESEL;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(
        uniqueConstraints = {@UniqueConstraint(columnNames = {"pesel"})}
)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Patient {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String LastName;
    @AttributeOverrides({
            @AttributeOverride(name = "city",column = @Column(name = "HOME_CITY")),
            @AttributeOverride(name = "street", column = @Column(name = "HOME_STREET")),
            @AttributeOverride(name = "apartmentNo", column = @Column(name = "HOME_APARTMENT_NO")),
            @AttributeOverride(name = "postalCode", column = @Column(name = "HOME_POSTAL_CODE"))
    })
    @Valid
    private Address homeAddress;
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "CORRESPONDENCE_CITY")),
            @AttributeOverride(name = "street", column = @Column(name = "CORRESPONDENCE_STREET")),
            @AttributeOverride(name = "apartmentNo", column = @Column(name = "CORRESPONDENCE_APARTMENT_NO")),
            @AttributeOverride(name = "postalCode", column = @Column(name = "CORRESPONDENCE_POSTAL_CODE"))
    })
    private Address correspondenceAddress;
    @PESEL
    private String pesel;
    @Pattern(regexp = "^\\d{9}$", message = "Phone Number should have 9 numbers")
    private String phoneNumber;
    @Email
    private String email;
    private String job;
    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;
    @AttributeOverrides({
            @AttributeOverride(name = "firstName", column = @Column(name = "CONTACT_PERSON_FIRST_NAME")),
            @AttributeOverride(name = "lastName", column = @Column(name = "CONTACT_PERSON_LAST_NAME")),
            @AttributeOverride(name = "phoneNumber", column = @Column(name = "CONTACT_PERSON_PHONE_NUMBER"))
    })
    @Valid
    private ContactPerson contactPerson;
    @OneToOne (mappedBy = "patient", orphanRemoval = true)
    @JsonManagedReference
    private HealthQuestionnaire healthQuestionnaire;
    @OneToMany(mappedBy = "patient", orphanRemoval = true)
    @JsonManagedReference
    private List<PatientObservationList> patientObservationLists;
    @ManyToOne
    @JoinColumn(name = "hospital_room_id")
    private HospitalRoom hospitalRoom;
    @ManyToOne()
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
    @JsonIgnore
    private ObjectAudit objectAudit = new ObjectAudit();

}
