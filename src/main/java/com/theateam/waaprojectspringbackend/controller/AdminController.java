package com.theateam.waaprojectspringbackend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
@CrossOrigin
public class AdminController {

    @RequestMapping("/dashboard")
    public String dashboard() {
        return "Greetings from dashboard!";
    }
}
