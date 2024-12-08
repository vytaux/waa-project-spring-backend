package com.theateam.waaprojectspringbackend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@CrossOrigin
public class DefaultController {

    @GetMapping
    public String home() {
        return "Hello World!<br>This is the API for the NextHome project.<br>See all the details here <a href=\"https://github.com/vytaux/waa-project-spring-backend\">https://github.com/vytaux/waa-project-spring-backend</a>.";
    }
}
