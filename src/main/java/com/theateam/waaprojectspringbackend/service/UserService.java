package com.theateam.waaprojectspringbackend.service;

import com.theateam.waaprojectspringbackend.entity.User;
import com.theateam.waaprojectspringbackend.entity.dto.request.MessageRequest;
import com.theateam.waaprojectspringbackend.entity.dto.response.MessageResponse;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUser();

    void approveUser(Long userId);

    void saveUser(Optional<User> user);

    Optional<User> getUserByEmail(String userEmail);

    List<MessageResponse> getAllMessages(long id);

    MessageResponse saveMessage(long id, MessageRequest request);
}
