package com.theateam.waaprojectspringbackend.service.impl;

import com.theateam.waaprojectspringbackend.entity.Message;
import com.theateam.waaprojectspringbackend.entity.MessageSession;
import com.theateam.waaprojectspringbackend.entity.User;
import com.theateam.waaprojectspringbackend.entity.UserStatus;
import com.theateam.waaprojectspringbackend.repository.UserRepo;
import com.theateam.waaprojectspringbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final MessageSessionRepo messageSessionRepo;
    private final MessageRepo messageRepo;
    private final AuthUtil authUtil;
    private final ModelMapper modelMapper;

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

    public void saveUser(Optional<User> optionalUser){
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            userRepo.save(user);
        }else{
            throw new IllegalArgumentException("Cannot Save empty Optional User");
        }
    }

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
