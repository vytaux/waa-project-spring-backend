package com.theateam.waaprojectspringbackend.controller;

import com.theateam.waaprojectspringbackend.entity.Property;
import com.theateam.waaprojectspringbackend.entity.dto.response.PropertyDetailsResponseDto;
import com.theateam.waaprojectspringbackend.entity.dto.response.PropertyResponseDto;
import com.theateam.waaprojectspringbackend.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@CrossOrigin
public class PublicController {

    private final PropertyService propertyService;

    @RequestMapping("/properties")
    public List<PropertyResponseDto> findAllProperties() {
        return propertyService.findAllProperties();
    }

    @RequestMapping("/properties/{slug}")
    public PropertyDetailsResponseDto getPropertyDetails(@PathVariable String slug) {
        return propertyService.getPropertyDetails(slug);
    }
}
