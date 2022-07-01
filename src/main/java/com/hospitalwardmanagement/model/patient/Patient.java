package com.hospitalwardmanagement.model.patient;

import com.hospitalwardmanagement.model.healthQuestionnaire.HealthQuestionnaire;
import com.hospitalwardmanagement.model.hospitalroom.HospitalRoom;
import com.hospitalwardmanagement.model.patientObservationList.PatientObservationLists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.pl.PESEL;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import java.sql.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(
        uniqueConstraints = {@UniqueConstraint(columnNames = {"pesel"})}
)
public class Patient {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    Long id;
    String firstName;
    String LastName;
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "HOME_CITY")),
            @AttributeOverride(name = "street", column = @Column(name = "HOME_STREET")),
            @AttributeOverride(name = "apartmentNo", column = @Column(name = "HOME_APARTMENT_NO")),
            @AttributeOverride(name = "postalCode", column = @Column(name = "HOME_POSTAL_CODE"))
    })
    Address homeAddress;
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "CORRESPONDENCE_CITY")),
            @AttributeOverride(name = "street", column = @Column(name = "CORRESPONDENCE_STREET")),
            @AttributeOverride(name = "apartmentNo", column = @Column(name = "CORRESPONDENCE_APARTMENT_NO")),
            @AttributeOverride(name = "postalCode", column = @Column(name = "CORRESPONDENCE_POSTAL_CODE"))
    })
    Address correspondenceAddress;
    @PESEL
    String pesel;
    @Digits(integer = 9, fraction = 0)
    String phoneNumber;
    @Email
    String email;
    String job;
    @Enumerated(EnumType.STRING)
    MaritalStatus maritalStatus;
    @CreationTimestamp
    Date creationDate;
    @UpdateTimestamp
    Date updateDate;

    @OneToOne
    @JoinColumn(name = "health_questionnaire_id")
    HealthQuestionnaire healthQuestionnaire;
    @OneToMany(mappedBy = "patient")
    List<PatientObservationLists> patientObservationListsList;

    @ManyToOne
    @JoinColumn(name = "hospital_room_id")
    HospitalRoom hospitalRoom;


}