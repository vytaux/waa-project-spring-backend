package com.theateam.waaprojectspringbackend.entity.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfferResponseDto {
    private long id;
    private String message;
    private String price;
    private String status;
    private UserResponseDto customer;
    private PropertyResponseDto property;
}
