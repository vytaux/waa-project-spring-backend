package com.theateam.waaprojectspringbackend.controller;

import com.theateam.waaprojectspringbackend.entity.User;
import com.theateam.waaprojectspringbackend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PublicController {

    @RequestMapping("/properties")
    public String properties() {
        return "Greetings from PublicController!";
    }
}
