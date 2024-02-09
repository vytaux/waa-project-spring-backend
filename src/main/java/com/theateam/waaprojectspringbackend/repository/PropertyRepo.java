package com.theateam.waaprojectspringbackend.repository;

import com.theateam.waaprojectspringbackend.entity.Property;
import com.theateam.waaprojectspringbackend.entity.PropertyStatus;
import com.theateam.waaprojectspringbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface PropertyRepo extends JpaRepository<Property, Long>, JpaSpecificationExecutor<Property> {
    Optional<Property> findBySlug(String slug);
    Optional<Property> findByIdAndStatusIn(Long propertyId, List<PropertyStatus> statuses);
    List<Property> findAllByOwnerEmail(String email);

//    @Query("SELECT p FROM Property p WHERE " +
//            "(:name IS NULL OR p.name LIKE %:name%) AND " +
//            "(:description IS NULL OR p.description LIKE %:description%) AND " +
//            "(:minPrice IS NULL OR p.price>= :minPrice) AND " +
//            "(:maxPrice IS NULL OR p.price<= :maxPrice)")
//    List<Property> searchProperties(String name, String description, BigDecimal minPrice, BigDecimal maxPrice);
}