package com.hospitalwardmanagement.model.patientObservation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "patient_observations")
public class patientObservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String bloodPressure;
    String bodyTemperature;
    String shortSummary;

}
