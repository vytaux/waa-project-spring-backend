package com.theateam.waaprojectspringbackend.service;

import com.theateam.waaprojectspringbackend.entity.RoleType;
import com.theateam.waaprojectspringbackend.entity.User;
import com.theateam.waaprojectspringbackend.entity.UserStatus;
import com.theateam.waaprojectspringbackend.entity.dto.request.MessageRequest;
import com.theateam.waaprojectspringbackend.entity.dto.request.SavePropertyRequestDto;
import com.theateam.waaprojectspringbackend.entity.dto.response.MessageResponse;
import com.theateam.waaprojectspringbackend.entity.dto.response.PropertyResponseDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findPendingOwners();
    void approveUser(Long userId);
    Optional<User> getUserByEmail(String userEmail);
    List<MessageResponse> getAllMessages(long id);
    MessageResponse saveMessage(long id, MessageRequest request);
    List<PropertyResponseDto> getSavedProperties();
    void addToSavedPropertiesList(SavePropertyRequestDto savePropertyRequestDto);
    void removePropertyFromSavedList(Long propertyId);
}
