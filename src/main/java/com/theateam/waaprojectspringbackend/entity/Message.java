package com.theateam.waaprojectspringbackend.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany
    private List<MessageSession> messageSessionIdList;
    private String message;
    private LocalDateTime createdAt;
    @ManyToOne
    private User createdBy;
}
