package com.theateam.waaprojectspringbackend.entity.dto.request;

import com.theateam.waaprojectspringbackend.entity.Message;
import com.theateam.waaprojectspringbackend.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageSessionRequest {
    private User userOne;
    private User userTwo;
    private List<Message> messages;
}
