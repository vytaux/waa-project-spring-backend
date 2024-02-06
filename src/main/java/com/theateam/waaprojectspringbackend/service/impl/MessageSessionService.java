package com.theateam.waaprojectspringbackend.service.impl;

import com.theateam.waaprojectspringbackend.entity.MessageSession;
import com.theateam.waaprojectspringbackend.entity.dto.request.MessageSessionRequest;
import com.theateam.waaprojectspringbackend.entity.dto.response.MessageSessionResponse;
import com.theateam.waaprojectspringbackend.exception.ResourceNotFoundException;
import com.theateam.waaprojectspringbackend.repository.MessageSessionRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessageSessionService {

    private final MessageSessionRepo repo;
    private final ModelMapper modelMapper;


    public List<MessageSessionResponse> getAll() {
        return repo.findAll().stream()
                .map(messageSession -> modelMapper.map(messageSession, MessageSessionResponse.class))
                .collect(Collectors.toList());
    }

    public MessageSessionResponse save(MessageSessionRequest request) {
        MessageSession messageSession = modelMapper.map(request, MessageSession.class);
        return modelMapper.map(repo.save(messageSession), MessageSessionResponse.class);
    }

    public MessageSessionResponse getById(int id) {
        MessageSession messageSession = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Message Session", id));
        return modelMapper.map(messageSession, MessageSessionResponse.class);
    }

    public MessageSessionResponse update(int id, MessageSessionRequest request) {
        repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Message Session", id));
        MessageSession messageSession = modelMapper.map(request, MessageSession.class);
        messageSession.setId(id);
        return modelMapper.map(repo.save(messageSession), MessageSessionResponse.class);
    }

    public void delete(int id) {
        repo.deleteById(id);
    }

}
