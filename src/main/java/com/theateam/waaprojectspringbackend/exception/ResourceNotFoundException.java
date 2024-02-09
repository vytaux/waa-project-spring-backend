package com.theateam.waaprojectspringbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ResourceNotFoundException extends ResponseStatusException {

    public ResourceNotFoundException(String resource, int id) {
        super(HttpStatus.NOT_FOUND, String.format("%s with id: %s Not Found", resource, id));
    }

    public ResourceNotFoundException(String resource) {
        super(HttpStatus.NOT_FOUND, resource + "%s Not Found");
    }
}
