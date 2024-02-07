package com.theateam.waaprojectspringbackend.entity.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PropertyResponseDto {
    long id;
    String name;
    String description;
    String slug;
    BigDecimal price;
    String status;
}