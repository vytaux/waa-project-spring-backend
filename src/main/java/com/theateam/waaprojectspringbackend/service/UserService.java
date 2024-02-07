package com.theateam.waaprojectspringbackend.service;

import com.theateam.waaprojectspringbackend.entity.dto.request.MessageRequest;
import com.theateam.waaprojectspringbackend.entity.dto.response.MessageResponse;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface UserService {
    List<MessageResponse> getAllMessages(@PathVariable long id);

    MessageResponse saveMessage(long id, MessageRequest request);
}
