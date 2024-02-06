package com.theateam.waaprojectspringbackend.service;

import com.theateam.waaprojectspringbackend.entity.*;
import com.theateam.waaprojectspringbackend.repository.PropertyRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PropertyService {

    private final PropertyRepo propertyRepo;

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
}
