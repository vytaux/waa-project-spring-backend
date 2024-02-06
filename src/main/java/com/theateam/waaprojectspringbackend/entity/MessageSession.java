package com.theateam.waaprojectspringbackend.entity;

import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class MessageSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_one")
    private User userOne;

    @ManyToOne
    @JoinColumn(name = "user_two")
    private User userTwo;

    @OneToMany
    @JoinColumn(name = "message_session_id")
    private List<Message> messages;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
