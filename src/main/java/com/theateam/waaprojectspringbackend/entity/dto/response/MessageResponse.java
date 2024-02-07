package com.theateam.waaprojectspringbackend.entity.dto.response;

import com.theateam.waaprojectspringbackend.entity.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageResponse {

    private int id;
    private String message;
    private LocalDateTime createdAt;
    private String createdBy;

    public static List<MessageResponse> fromEntityToDto(List<Message> messages) {
        return messages.stream()
                .map(MessageResponse::fromEntityToDto)
                .collect(Collectors.toList());
    }

    public static MessageResponse fromEntityToDto(Message message) {
        return new MessageResponse(message.getId(), message.getMessage(), message.getCreatedAt(), message.getCreatedBy().getEmail());
    }

}
