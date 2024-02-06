package com.theateam.waaprojectspringbackend.repository;

import com.theateam.waaprojectspringbackend.entity.Property;
import com.theateam.waaprojectspringbackend.entity.PropertyStatus;
import com.theateam.waaprojectspringbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PropertyRepo extends JpaRepository<Property, Long> {
    Optional<Property> findBySlug(String slug);
    Optional<Property> findByIdAndStatusIn(Long propertyId, List<PropertyStatus> statuses);
    List<Property> findAllByOwnerEmail(String email);
}