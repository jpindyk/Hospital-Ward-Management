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


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn
    @JsonIgnore
    private User createdByUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    @JsonIgnore
    private User lastChangeByUser;
}
