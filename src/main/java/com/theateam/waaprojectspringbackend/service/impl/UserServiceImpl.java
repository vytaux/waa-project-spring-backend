package com.theateam.waaprojectspringbackend.service.impl;

import com.theateam.waaprojectspringbackend.entity.*;
import com.theateam.waaprojectspringbackend.entity.dto.request.MessageRequest;
import com.theateam.waaprojectspringbackend.entity.dto.request.SavePropertyRequestDto;
import com.theateam.waaprojectspringbackend.entity.dto.response.MessageResponse;
import com.theateam.waaprojectspringbackend.repository.MessageRepo;
import com.theateam.waaprojectspringbackend.repository.MessageSessionRepo;
import com.theateam.waaprojectspringbackend.repository.PropertyRepo;
import com.theateam.waaprojectspringbackend.repository.UserRepo;
import com.theateam.waaprojectspringbackend.service.UserService;
import com.theateam.waaprojectspringbackend.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final MessageSessionRepo messageSessionRepo;
    private final MessageRepo messageRepo;
    private final AuthUtil authUtil;
    private final ModelMapper modelMapper;
    private final PropertyRepo propertyRepo;

    @Override
    public List<User> getAllUser() {
       return userRepo.findAll();
    }

    @Override
    public void approveUser(Long userId) {
            User user = userRepo.findById(userId).orElseThrow();
            user.setStatus(UserStatus.STATUS_APPROVED);
            userRepo.save(user);
    }

    public Optional<User> getUserByEmail(String userEmail){
        return userRepo.findByEmail(userEmail);
    }

    public void addToSavedPropertiesList(String email, SavePropertyRequestDto savePropertyRequestDto) {
        User user = userRepo.findByEmail(email).orElseThrow();

        Property property = propertyRepo
                .findById(savePropertyRequestDto.getPropertyId()).orElseThrow();

        if (user.getSavedProperties().contains(property)) {
            return;
        }

        user.getSavedProperties().add(property);
        userRepo.save(user);
    }

    @Override
    public List<MessageResponse> getAllMessages(long id) {
        User authenticatedUser = authUtil.getUser();
        User secondUser = userRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found"));
        MessageSession messageSession = findMessageSessionOrCreate(authenticatedUser, secondUser);
        return MessageResponse.fromEntityToDto(messageSession.getMessages());
    }

    @Override
    public MessageResponse saveMessage(long id, MessageRequest request) {
        User authenticatedUser = authUtil.getUser();
        User secondUser = userRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found"));
        MessageSession messageSession = findMessageSessionOrCreate(authenticatedUser, secondUser);

        Message message = modelMapper.map(request, Message.class);
        message.setMessageSession(messageSession);
        message.setCreatedBy(authUtil.getUser());

        return MessageResponse.fromEntityToDto(messageRepo.save(message));
    }

    @Override
    public void removePropertyFromSavedList(String email, Long itemId) {
        User user = userRepo.findByEmail(email).orElseThrow();
        Property property = propertyRepo.findById(itemId).orElseThrow();
        user.getSavedProperties().remove(property);
        userRepo.save(user);
    }

    private MessageSession findMessageSessionOrCreate(User authenticatedUser, User secondUser) {
        MessageSession messageSession = messageSessionRepo.findByUserOneAndUserTwoId(authenticatedUser.getId(), secondUser.getId());
        if (messageSession == null)
            messageSession = messageSessionRepo.save(new MessageSession(authenticatedUser, secondUser));

        return messageSession;
    }

}
