package com.theateam.waaprojectspringbackend.service.impl;

import com.theateam.waaprojectspringbackend.entity.Message;
import com.theateam.waaprojectspringbackend.entity.MessageSession;
import com.theateam.waaprojectspringbackend.entity.Property;
import com.theateam.waaprojectspringbackend.entity.User;
import com.theateam.waaprojectspringbackend.entity.dto.request.MessageSessionRequest;
import com.theateam.waaprojectspringbackend.entity.dto.response.MessageSessionResponse;
import com.theateam.waaprojectspringbackend.exception.ResourceNotFoundException;
import com.theateam.waaprojectspringbackend.repository.MessageRepo;
import com.theateam.waaprojectspringbackend.repository.MessageSessionRepo;
import com.theateam.waaprojectspringbackend.repository.PropertyRepo;
import com.theateam.waaprojectspringbackend.repository.UserRepo;
import com.theateam.waaprojectspringbackend.service.MessageSessionService;
import com.theateam.waaprojectspringbackend.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageSessionServiceImpl implements MessageSessionService {

    private final MessageSessionRepo repo;
    private final MessageRepo messageRepo;
    private final ModelMapper modelMapper;
    private final AuthUtil authUtil;
    private final UserRepo userRepo;
    private final PropertyRepo propertyRepo;

    public List<MessageSessionResponse> getAll() {
        List<MessageSession> messageSessions = repo.findAllByUserEmail(authUtil.getUserDetails().getUsername());
        return MessageSessionResponse.fromEntityToDto(messageSessions);
    }

    private final MessageSessionRepo messageSessionRepo;

    private MessageSession findMessageSessionOrCreate(User authenticatedUser, User secondUser) {
        MessageSession messageSession = messageSessionRepo.findByUserOneAndUserTwoId(authenticatedUser.getId(), secondUser.getId());
        if (messageSession == null)
            messageSession = messageSessionRepo.save(new MessageSession(authenticatedUser, secondUser));

        return messageSession;
    }

    public void save(MessageSessionRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepo.findByEmail(authentication.getName()).orElseThrow();
        Property property = propertyRepo.findById(request.getPropertyId()).orElseThrow();

        // Yea need to fix all of this
        MessageSession messageSession = findMessageSessionOrCreate(user, property.getOwner());

        Message message = new Message();
        message.setMessage(request.getMessage());
        message.setCreatedBy(user);
        message.setMessageSession(messageSession);
        messageRepo.save(message);

        messageSession.getMessages().add(message);
        repo.save(messageSession);
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
