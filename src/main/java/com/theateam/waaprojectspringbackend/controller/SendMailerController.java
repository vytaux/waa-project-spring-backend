package com.theateam.waaprojectspringbackend.controller;

import com.theateam.waaprojectspringbackend.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

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
