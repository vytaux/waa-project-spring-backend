package com.theateam.waaprojectspringbackend.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String message;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToOne
    private User createdBy;
}
