package com.theateam.waaprojectspringbackend.service.impl;

import com.theateam.waaprojectspringbackend.entity.MessageSession;
import com.theateam.waaprojectspringbackend.entity.dto.request.MessageSessionRequest;
import com.theateam.waaprojectspringbackend.entity.dto.response.MessageSessionResponse;
import com.theateam.waaprojectspringbackend.exception.ResourceNotFoundException;
import com.theateam.waaprojectspringbackend.repository.MessageSessionRepo;
import com.theateam.waaprojectspringbackend.service.MessageSessionService;
import com.theateam.waaprojectspringbackend.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageSessionServiceImpl implements MessageSessionService {

    @Autowired
    MessageSessionRepo repo;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    AuthUtil authUtil;


    public List<MessageSessionResponse> getAll() {
        List<MessageSession> messageSessions = repo.findAllByUserEmail(authUtil.getUserDetails().getUsername());
        return MessageSessionResponse.fromEntityToDto(messageSessions);
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
