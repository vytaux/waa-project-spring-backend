package com.theateam.waaprojectspringbackend.entity.dto.response;

import com.theateam.waaprojectspringbackend.entity.Message;
import com.theateam.waaprojectspringbackend.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageSessionResponse {
    private int id;
    private User userOne;
    private User userTwo;
    private List<Message> messages;
    private LocalDateTime updatedAt;
}
