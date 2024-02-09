package com.theateam.waaprojectspringbackend.controller;

import com.theateam.waaprojectspringbackend.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SendMailerController {

    private final EmailService emailService;

    @PostMapping("/sendEmail")
    public ResponseEntity<String> sendEmail(@RequestBody Map<String, Object> request) {
        return emailService.sendEmailFromRequest(request);
    }
}
