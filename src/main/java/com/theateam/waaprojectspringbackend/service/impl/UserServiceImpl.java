package com.theateam.waaprojectspringbackend.service.impl;

import com.theateam.waaprojectspringbackend.entity.User;
import com.theateam.waaprojectspringbackend.entity.UserStatus;
import com.theateam.waaprojectspringbackend.repository.UserRepo;
import com.theateam.waaprojectspringbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

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

}
