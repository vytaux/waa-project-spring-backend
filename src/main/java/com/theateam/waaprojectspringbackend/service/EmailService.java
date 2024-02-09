package com.theateam.waaprojectspringbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.email.sender}")
    private String senderEmail;

    @Async
    public void sendEmail(String recipient, String subject, String body) {
        sendEmail(Collections.singletonList(recipient), subject, body);
    }

    @Async
    public void sendEmail(List<String> recipients, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(senderEmail);
        message.setTo(recipients.toArray(new String[0]));
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }

    @Async
    public ResponseEntity<String> sendEmailFromRequest(@RequestBody Map<String, Object> request) {
        String subject = (String) request.get("subject");
        String body = (String) request.get("body");

        List<String> recipients = (List<String>) request.get("recipients");

        if (subject != null && body != null) {
            if (recipients != null && !recipients.isEmpty()) {
                sendEmail(recipients, subject, body);
                return ResponseEntity.ok("Emails sent successfully to multiple recipients");
            } else {
                return ResponseEntity.badRequest().body("No recipients provided");
            }
        } else {
            return ResponseEntity.badRequest().body("Subject and body are required");
        }
    }
}
