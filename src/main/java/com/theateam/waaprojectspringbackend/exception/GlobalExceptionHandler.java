package com.theateam.waaprojectspringbackend.exception;

import com.theateam.waaprojectspringbackend.entity.dto.response.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ApiErrorResponse> handleResponseStatusException(ResponseStatusException ex) {
        HttpStatus status = ex.getStatus();
        ApiErrorResponse body = new ApiErrorResponse(true, ex.getRawStatusCode(), ex.getReason());
        return ResponseEntity.status(status).body(body);
    }

}
