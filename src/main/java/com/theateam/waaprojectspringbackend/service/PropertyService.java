package com.theateam.waaprojectspringbackend.service;

import com.theateam.waaprojectspringbackend.entity.*;
import com.theateam.waaprojectspringbackend.entity.dto.request.PropertyRequestDto;
import com.theateam.waaprojectspringbackend.repository.PropertyRepo;
import com.theateam.waaprojectspringbackend.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PropertyService {

    private final PropertyRepo propertyRepo;
    private final ModelMapper modelMapper;
    private final UserRepo userRepo;

    public void turnPropertyContingent(Long propertyId) {
        Property property = propertyRepo.findById(propertyId).orElseThrow();
        property.setStatus(PropertyStatus.STATUS_CONTINGENT);
        propertyRepo.save(property);
    }

    public void cancelPropertyContingency(Long propertyId) {
        Property property = propertyRepo.findById(propertyId).orElseThrow();
        property.setStatus(PropertyStatus.STATUS_AVAILABLE);
        propertyRepo.save(property);
    }

    public List<Property> findAllByOwnerEmail(String email) {
        return propertyRepo.findAllByOwnerEmail(email);
    }

    public void create(String email, PropertyRequestDto propertyRequestDto) {
        User user = userRepo.findByEmail(email).orElseThrow();
        Property property = modelMapper.map(propertyRequestDto, Property.class);
        property.setStatus(PropertyStatus.STATUS_AVAILABLE);
        property.setOwner(user);
        propertyRepo.save(property);
    }

    public void update(String email, Long propertyId, PropertyRequestDto propertyRequestDto) {
        Property property = propertyRepo.findById(propertyId).orElseThrow();
        modelMapper.map(propertyRequestDto, property);
        propertyRepo.save(property);
    }
}
