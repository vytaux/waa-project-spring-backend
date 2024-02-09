package com.theateam.waaprojectspringbackend.service;

import com.theateam.waaprojectspringbackend.entity.Property;
import com.theateam.waaprojectspringbackend.entity.User;
import com.theateam.waaprojectspringbackend.entity.dto.request.PropertyRequestDto;
import com.theateam.waaprojectspringbackend.entity.dto.response.PropertyDetailsResponseDto;
import com.theateam.waaprojectspringbackend.entity.dto.response.PropertyResponseDto;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface PropertyService {
    List<Property> findAllByOwnerEmail();
    List<PropertyResponseDto> findAllProperties(String name, String description, BigDecimal minPrice, BigDecimal maxPrice);
    PropertyDetailsResponseDto getPropertyDetails(String slug);
    void turnPropertyContingent(Long propertyId);
    void cancelPropertyContingency(Long propertyId);
    ResponseEntity<String> create(PropertyRequestDto propertyRequestDto);
    void update(Long propertyId, PropertyRequestDto propertyRequestDto);
    void delete(Long propertyId);
    User getUserByPropertySlug(String propertySlug);

}
