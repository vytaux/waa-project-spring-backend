package com.theateam.waaprojectspringbackend.entity.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateOfferDto {
    private String message;
    private BigDecimal price;
}
