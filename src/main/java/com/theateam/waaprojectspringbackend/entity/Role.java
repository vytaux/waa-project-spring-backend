package com.theateam.waaprojectspringbackend.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private RoleName name;

    public enum RoleName {
        ROLE_ADMIN,
        ROLE_OWNER,
        ROLE_CUSTOMER
    }
}
