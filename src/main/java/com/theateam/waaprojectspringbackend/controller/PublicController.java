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
    private final ModelMapper modelMapper;

    @RequestMapping("/properties")
    public List<PropertyResponseDto> properties() {
        List<Property> allProperties = propertyService.findAllProperties();

        return allProperties.stream()
                .map(property -> modelMapper.map(property, PropertyResponseDto.class))
                .collect(Collectors.toList());
    }

    @RequestMapping("/properties/{slug}")
    public PropertyDetailsResponseDto getPropertyDetails(@PathVariable String slug) {
        Property property = propertyService.findPropertyBySlug(slug).orElseThrow();
        return modelMapper.map(property, PropertyDetailsResponseDto.class);
    }
}
