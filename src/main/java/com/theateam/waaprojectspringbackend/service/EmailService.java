package com.theateam.waaprojectspringbackend.service;

import org.modelmapper.internal.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.email.sender}")
    private String senderEmail;

    public void sendEmail(List<String> recipients, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(senderEmail);
        message.setTo(recipients.toArray(new String[0]));
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }

    public void sendEmail(String recipient, String subject, String body) {
        sendEmail(Collections.singletonList(recipient), subject, body);
    }

    public void sendHtmlEmailWithAttachment(String subject,
                                            String body,
                                            List<String> recipients,
                                            Map<String, String> placeholders, MultipartFile attachment) throws MessagingException, IOException {
        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        // Setting sender and recipients
        helper.setFrom(senderEmail);
        helper.setTo(recipients.toArray(new String[0]));

        // Setting subject
        helper.setSubject(subject);

        // With Attachment
        if (attachment != null && attachment.getOriginalFilename() != null && !attachment.isEmpty()) {
            // Convert MultipartFile to DataSource
            DataSource dataSource = new FileDataSource(convertMultipartFileToFile(attachment));
            helper.addAttachment(attachment.getOriginalFilename(), dataSource);
        }

        String htmlTemplate = readFile("emailTemplate.html");

        for (Map.Entry<String, String> entry : placeholders.entrySet()) {
            htmlTemplate = htmlTemplate.replace("${" + entry.getKey() + "}", entry.getValue());
        }

// Additional content
        String additionalContent = "<p>This is additional content added dynamically.</p>";

// Replace the additional content placeholder
        htmlTemplate = htmlTemplate.replace("${additionalContent}", additionalContent);

// Setting HTML content
        helper.setText(htmlTemplate, true);

        mailSender.send(message);
    }

    private String readFile(String filePath) throws IOException {
        try (InputStream inputStream = ResourceUtils.getURL("classpath:" + filePath).openStream()) {
            return new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8)).lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
//            log.error("Failed to read file: {}", filePath, e);
            throw new IOException("Failed to read file: " + filePath, e);
        }
    }


    private File convertMultipartFileToFile(MultipartFile file) throws IOException {
        try (InputStream inputStream = file.getInputStream()) {
            Path tempFile = Files.createTempFile("temp-", "-" + file.getOriginalFilename());
            Files.copy(inputStream, tempFile, StandardCopyOption.REPLACE_EXISTING);
            return tempFile.toFile();
        }
    }

}
