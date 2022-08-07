package com.hospitalwardmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hospitalwardmanagement.model.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Embeddable
@Setter
@Getter
public class ObjectAudit {

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;

    @PrePersist
    void prePersist(){
        createdOn = LocalDateTime.now();
    }

    @PreUpdate
    void preUpdate() {
        updatedOn = LocalDateTime.now();
    }


    @Column(nullable = false)
    private String createdByUser;

    private String lastChangeByUser;
}
