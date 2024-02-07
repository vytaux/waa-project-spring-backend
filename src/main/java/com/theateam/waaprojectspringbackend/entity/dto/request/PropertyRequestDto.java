package com.theateam.waaprojectspringbackend.entity.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PropertyRequestDto {
    private String name;
    private String slug;
    private String description;
    private BigDecimal price;
}
