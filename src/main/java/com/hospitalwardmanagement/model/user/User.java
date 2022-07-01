package com.hospitalwardmanagement.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String firstName;
    String lastName;


    public String getLogin () {
        return firstName.substring(0,1) + lastName;
    }

    @Enumerated (EnumType.STRING)
    UserRole userRole;


}
