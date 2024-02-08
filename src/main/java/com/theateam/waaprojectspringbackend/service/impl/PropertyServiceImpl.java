package com.theateam.waaprojectspringbackend.service.impl;

import com.theateam.waaprojectspringbackend.entity.*;
import com.theateam.waaprojectspringbackend.entity.dto.request.PropertyRequestDto;
import com.theateam.waaprojectspringbackend.repository.PropertyRepo;
import com.theateam.waaprojectspringbackend.repository.UserRepo;
import com.theateam.waaprojectspringbackend.service.PropertyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PropertyServiceImpl implements PropertyService {

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
        if (!user.getStatus().equals(UserStatus.STATUS_APPROVED)) {
            throw new RuntimeException("User is not approved");
        }

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

    public void delete(Long propertyId) {
        Property property = propertyRepo.findById(propertyId).orElseThrow();
        if (property.getStatus().equals(PropertyStatus.STATUS_PENDING)) {
            return;
        }

        propertyRepo.deleteById(propertyId);
    }

    @Override
    public List<Property> findAllProperties() {
        return propertyRepo.findAll();
    }

    @Override
    public Optional<Property> findPropertyBySlug(String slug) {
        return propertyRepo.findBySlug(slug);
    }
}
