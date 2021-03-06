package com.hospitalwardmanagement.model.user;

import com.hospitalwardmanagement.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;


public enum UserRole {
    ROLE_ADMIN(0),
    ROLE_NURSE_WARD(1),
    ROLE_NURSE(2),
    ROLE_REGISTRAR(3),
    ROLE_DEFAULT_USER(4);

    UserRole(int nr) {
        this.nr = nr;
    }

    private int nr;


    public static UserRole ofNr(int nr) {
        UserRole[] userRoles = values();
        for (UserRole userRole : userRoles) {
            if (userRole.nr == nr) {
                return userRole;
            }
        }
        throw new ResourceNotFoundException("Role", "id", nr);
    }



}
