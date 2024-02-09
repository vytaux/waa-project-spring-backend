package com.theateam.waaprojectspringbackend.controller;

import com.theateam.waaprojectspringbackend.entity.User;
import com.theateam.waaprojectspringbackend.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/properties")
@CrossOrigin
@RequiredArgsConstructor
public class PropertyController {

    private final PropertyService service;

    @GetMapping("{slug}/user")
    public User getUserByPropertySlug(@PathVariable String slug) {
        return service.getUserByPropertySlug(slug);
    }

}
