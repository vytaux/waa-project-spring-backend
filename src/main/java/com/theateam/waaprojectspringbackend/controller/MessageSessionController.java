package com.theateam.waaprojectspringbackend.controller;

import com.theateam.waaprojectspringbackend.entity.dto.request.MessageSessionRequest;
import com.theateam.waaprojectspringbackend.entity.dto.response.MessageSessionResponse;
import com.theateam.waaprojectspringbackend.service.impl.MessageSessionServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/message-sessions")
@RequiredArgsConstructor
@CrossOrigin
public class MessageSessionController {

    private final MessageSessionServiceImpl service;

    @GetMapping
    public List<MessageSessionResponse> getAll() {
        return service.getAll();
    }

    @PostMapping
    public void save(@RequestBody MessageSessionRequest request) {
        service.save(request);
    }

    @GetMapping("{id}")
    public MessageSessionResponse getById(@PathVariable int id) {
        return service.getById(id);
    }

    @PutMapping("{id}")
    public MessageSessionResponse update(@PathVariable int id, @RequestBody MessageSessionRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
}
