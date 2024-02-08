package com.theateam.waaprojectspringbackend.controller;

import com.theateam.waaprojectspringbackend.entity.dto.request.MessageRequest;
import com.theateam.waaprojectspringbackend.entity.dto.response.MessageResponse;
import com.theateam.waaprojectspringbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {

    private final UserService userService;

    @GetMapping("{id}/messages")
    public List<MessageResponse> getAllMessages(@PathVariable long id) {
        return userService.getAllMessages(id);
    }

    @PostMapping("{id}/messages")
    public MessageResponse saveMessage(@PathVariable long id, @RequestBody MessageRequest request) {
        return userService.saveMessage(id, request);
    }
}
