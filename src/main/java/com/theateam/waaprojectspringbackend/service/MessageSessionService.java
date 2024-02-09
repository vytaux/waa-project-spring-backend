package com.theateam.waaprojectspringbackend.service;

import com.theateam.waaprojectspringbackend.entity.dto.request.MessageSessionRequest;
import com.theateam.waaprojectspringbackend.entity.dto.response.MessageSessionResponse;

import java.util.List;

public interface MessageSessionService {
    List<MessageSessionResponse> getAll();

    void save(MessageSessionRequest request);

    MessageSessionResponse getById(int id);

    MessageSessionResponse update(int id, MessageSessionRequest request);

    void delete(int id);
}
