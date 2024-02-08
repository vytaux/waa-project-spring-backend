package com.theateam.waaprojectspringbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_one_id")
    private User userOne;

    @ManyToOne
    @JoinColumn(name = "user_two_id")
    private User userTwo;

    @OneToMany
    @JoinColumn(name = "message_session_id")
    @OrderBy("createdAt ASC")
    private List<Message> messages = new ArrayList<>();

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public MessageSession(User userOne, User userTwo) {
        this.userOne = userOne;
        this.userTwo = userTwo;
    }

    public void addMessage(Message message) {
        messages.add(message);
    }
}
