package com.theateam.waaprojectspringbackend.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "properties", indexes = @Index(name = "slug", columnList = "slug"))
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String description;
    private String slug;

    @Column(precision = 10, scale = 2)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private PropertyStatus status;

    @ManyToOne
    private User owner;

    @OneToOne(mappedBy = "property")
    private Offer offer;
}