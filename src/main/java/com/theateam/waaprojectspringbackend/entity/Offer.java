package com.theateam.waaprojectspringbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "offers")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;
    private BigDecimal price;


    @JsonIgnore
    @ManyToOne
    private User customer;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private OfferStatus status;

    @JsonIgnore
    @ManyToOne
    private Property property;

}