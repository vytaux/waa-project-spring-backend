package com.theateam.waaprojectspringbackend.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class MessageSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToMany
    private List<User> userId1;
    @OneToMany
    private List<User> userId2;
    @OneToMany
    private List<Message> messages;
    private LocalDateTime updatedAt;
}
