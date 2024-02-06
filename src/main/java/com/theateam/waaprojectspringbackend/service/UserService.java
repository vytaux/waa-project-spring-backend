package com.theateam.waaprojectspringbackend.service;

import com.theateam.waaprojectspringbackend.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    List<User> getAllUser();

    void approveUser(Long userId);
}
