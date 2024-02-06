package com.theateam.waaprojectspringbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "offers")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String message;
    private BigDecimal price;

    @ManyToOne
    @JsonIgnore
    private User customer;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private OfferStatus status;

    @OneToOne
    @JsonIgnore
    private Property property;

}