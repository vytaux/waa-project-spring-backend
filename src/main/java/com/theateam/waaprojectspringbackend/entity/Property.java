package com.theateam.waaprojectspringbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

    @JsonIgnore
    @ManyToOne
    private User owner;

    @JsonIgnore
    @OneToMany(mappedBy = "property")
    private List<Offer> offers = new ArrayList<>();
}