package com.theateam.waaprojectspringbackend.entity.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Data
public class EmailRequest {
    private String subject;
    private String body;
    private List<String> recipients;
    private Map<String, String> placeholders;
    private MultipartFile attachment;
    // getters and setters
}
