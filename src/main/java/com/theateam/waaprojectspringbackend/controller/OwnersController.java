package com.theateam.waaprojectspringbackend.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/owners")
@CrossOrigin
public class OwnersController {

    @RequestMapping("/properties")
    public String properties() {
        return "Greetings from Owners properties!";
    }
}
