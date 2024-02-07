package com.theateam.waaprojectspringbackend.entity.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiErrorResponse {
    boolean error;
    int statusCode;
    String message;
}
