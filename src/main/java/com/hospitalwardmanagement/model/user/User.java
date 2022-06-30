package com.hospitalwardmanagement.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String firstName;
    String lastName;
    public String getLogin () {
        return firstName.substring(0,2) + lastName.substring(0,2);
    }
    @Enumerated (EnumType.STRING)
    UserRole userRole;


}
