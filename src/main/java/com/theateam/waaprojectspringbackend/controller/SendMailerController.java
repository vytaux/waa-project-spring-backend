package com.theateam.waaprojectspringbackend.controller;

import com.theateam.waaprojectspringbackend.entity.dto.request.EmailRequest;
import com.theateam.waaprojectspringbackend.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class SendMailerController {
    @Autowired
    private EmailService emailService;

    @PostMapping("/sendEmail")
    public ResponseEntity<String> sendEmail(@RequestBody Map<String, Object> request) {
        String subject = (String) request.get("subject");
        String body = (String) request.get("body");

        List<String> recipients = (List<String>) request.get("recipients");

        if (subject != null && body != null) {
            if (recipients != null && !recipients.isEmpty()) {
                emailService.sendEmail(recipients, subject, body);
                return ResponseEntity.ok("Emails sent successfully to multiple recipients");
            } else {
                return ResponseEntity.badRequest().body("No recipients provided");
            }
        } else {
            return ResponseEntity.badRequest().body("Subject and body are required");
        }
    }
}
