package com.theateam.waaprojectspringbackend.service;

import com.theateam.waaprojectspringbackend.entity.Property;
import com.theateam.waaprojectspringbackend.entity.User;
import com.theateam.waaprojectspringbackend.entity.dto.request.PropertyRequestDto;

import java.util.List;
import java.util.Optional;

public interface PropertyService {

    void turnPropertyContingent(Long propertyId);
    void cancelPropertyContingency(Long propertyId);
    List<Property> findAllByOwnerEmail(String email);

    void create(String email, PropertyRequestDto propertyRequestDto);

    void update(String email, Long propertyId, PropertyRequestDto propertyRequestDto);

    void delete(Long propertyId);

    List<Property> findAllProperties();

    Optional<Property> findPropertyBySlug(String slug);

    User getUserByPropertySlug(String propertySlug);
}
