package com.theateam.waaprojectspringbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    @JsonIgnore
    private MessageSession messageSession;

    @ManyToOne
    private User createdBy;
}
