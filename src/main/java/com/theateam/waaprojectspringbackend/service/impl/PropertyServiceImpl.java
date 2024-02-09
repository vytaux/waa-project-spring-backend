package com.theateam.waaprojectspringbackend.service.impl;

import com.theateam.waaprojectspringbackend.entity.*;
import com.theateam.waaprojectspringbackend.entity.dto.request.PropertyRequestDto;
import com.theateam.waaprojectspringbackend.entity.dto.response.PropertyDetailsResponseDto;
import com.theateam.waaprojectspringbackend.entity.dto.response.PropertyResponseDto;
import com.theateam.waaprojectspringbackend.exception.ResourceNotFoundException;
import com.theateam.waaprojectspringbackend.repository.PropertyRepo;
import com.theateam.waaprojectspringbackend.repository.UserRepo;
import com.theateam.waaprojectspringbackend.service.PropertyService;
import com.theateam.waaprojectspringbackend.specifications.PropertySpecifications;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepo propertyRepo;
    private final ModelMapper modelMapper;
    private final UserRepo userRepo;
    private final EntityManager entityManager;

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

    public List<Property> findAllByOwnerEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return propertyRepo.findAllByOwnerEmail(authentication.getName());
    }

    public ResponseEntity<String> create(PropertyRequestDto propertyRequestDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepo.findByEmail(authentication.getName()).orElseThrow();

        if (!user.getStatus().equals(UserStatus.STATUS_APPROVED)) {
            return ResponseEntity.badRequest().body("You need to be approved by our admin before you can list any properties");
        }

        Property property = modelMapper.map(propertyRequestDto, Property.class);
        property.setStatus(PropertyStatus.STATUS_AVAILABLE);
        property.setOwner(user);
        propertyRepo.save(property);
        return ResponseEntity.ok("Property Listed :)");
    }

    public void update(Long propertyId, PropertyRequestDto propertyRequestDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // todo prevent updating others property
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

    public List<PropertyResponseDto> findAllProperties(String name, String description, BigDecimal minPrice, BigDecimal maxPrice) {
        Specification<Property> spec = PropertySpecifications.searchProperties(name, description, minPrice, maxPrice);
        return propertyRepo.findAll(spec).stream()
                .map(property -> modelMapper.map(property, PropertyResponseDto.class))
                .collect(Collectors.toList());
    }

//    @Override
//    public List<PropertyResponseDto> findAllProperties() {
//        List<Property> allProperties = propertyRepo.findAll();
//
//        return allProperties.stream()
//                .map(property -> modelMapper.map(property, PropertyResponseDto.class))
//                .collect(Collectors.toList());
//    }

    @Override
    public PropertyDetailsResponseDto getPropertyDetails(String slug) {
        Property property = propertyRepo.findBySlug(slug).orElseThrow();
        return modelMapper.map(property, PropertyDetailsResponseDto.class);
    }

    public User getUserByPropertySlug(String propertySlug) {
        return propertyRepo.findBySlug(propertySlug)
                .orElseThrow(() -> new ResourceNotFoundException("Property"))
                .getOwner();
    }
}
