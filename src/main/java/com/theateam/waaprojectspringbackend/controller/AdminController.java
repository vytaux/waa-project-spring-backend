package com.theateam.waaprojectspringbackend.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@CrossOrigin
public class AdminController {

    @GetMapping("/dashboard")
    public String dashboard() {
        return "Greetings from admin/dashboard!";
    }

    @GetMapping("/owners/pending")
    public String adminOwnersPending() {
        return "Greetings from admin/owners/pending!";
    }

    @PutMapping("/admin/owners/:ownerId/approve")
    public String adminOwnersOwnerApprove() {
        return "Greetings from admin/owners/owner/approve!";
    }
}
