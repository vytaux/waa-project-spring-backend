package com.theateam.waaprojectspringbackend.service.impl;

import com.theateam.waaprojectspringbackend.entity.Message;
import com.theateam.waaprojectspringbackend.entity.MessageSession;
import com.theateam.waaprojectspringbackend.entity.User;
import com.theateam.waaprojectspringbackend.entity.dto.request.MessageRequest;
import com.theateam.waaprojectspringbackend.entity.dto.response.MessageResponse;
import com.theateam.waaprojectspringbackend.repository.MessageRepo;
import com.theateam.waaprojectspringbackend.repository.MessageSessionRepo;
import com.theateam.waaprojectspringbackend.repository.UserRepo;
import com.theateam.waaprojectspringbackend.service.UserService;
import com.theateam.waaprojectspringbackend.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo repo;
    private final MessageSessionRepo messageSessionRepo;
    private final MessageRepo messageRepo;
    private final AuthUtil authUtil;
    private final ModelMapper modelMapper;

    @Override
    public List<MessageResponse> getAllMessages(long id) {
        User authenticatedUser = authUtil.getUser();
        User secondUser = repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found"));
        MessageSession messageSession = findMessageSessionOrCreate(authenticatedUser, secondUser);
        return MessageResponse.fromEntityToDto(messageSession.getMessages());
    }

    @Override
    public MessageResponse saveMessage(long id, MessageRequest request) {
        User authenticatedUser = authUtil.getUser();
        User secondUser = repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found"));
        MessageSession messageSession = findMessageSessionOrCreate(authenticatedUser, secondUser);

        Message message = modelMapper.map(request, Message.class);
        message.setMessageSession(messageSession);
        message.setCreatedBy(authUtil.getUser());

        return MessageResponse.fromEntityToDto(messageRepo.save(message));
    }

    private MessageSession findMessageSessionOrCreate(User authenticatedUser, User secondUser) {
        MessageSession messageSession = messageSessionRepo.findByUserOneAndUserTwoId(authenticatedUser.getId(), secondUser.getId());
        if (messageSession == null)
            messageSession = messageSessionRepo.save(new MessageSession(authenticatedUser, secondUser));

        return messageSession;
    }
}
