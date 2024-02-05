package com.theateam.waaprojectspringbackend.service.impl;

import com.theateam.waaprojectspringbackend.repository.MessageSessionRepo;
import com.theateam.waaprojectspringbackend.service.MessageSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageSessionServiceImpl implements MessageSessionService {

    private final MessageSessionRepo repo;

}
