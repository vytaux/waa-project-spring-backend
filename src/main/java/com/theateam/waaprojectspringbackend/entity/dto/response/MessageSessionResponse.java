package com.theateam.waaprojectspringbackend.entity.dto.response;

import com.theateam.waaprojectspringbackend.entity.MessageSession;
import com.theateam.waaprojectspringbackend.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageSessionResponse {
    private int id;
    private User user;
    private List<MessageResponse> messages;
    private LocalDateTime updatedAt;

    public static List<MessageSessionResponse> fromEntityToDto(List<MessageSession> messageSessions) {
        return messageSessions.stream()
                .map(MessageSessionResponse::fromEntityToDto)
                .collect(Collectors.toList());
    }

    public static MessageSessionResponse fromEntityToDto(MessageSession messageSession) {
        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userDetails.getUsername().equals(messageSession.getUserOne().getEmail())
                ? messageSession.getUserTwo() : messageSession.getUserOne();

        List<MessageResponse> messageResponse = MessageResponse.fromEntityToDto(messageSession.getMessages());

        return new MessageSessionResponse(
                messageSession.getId(),
                user,
                messageResponse,
                messageSession.getUpdatedAt()
        );
    }
}
