package com.theateam.waaprojectspringbackend.controller;

import com.theateam.waaprojectspringbackend.entity.User;
import com.theateam.waaprojectspringbackend.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@CrossOrigin
@RequiredArgsConstructor
public class AdminController {

    private final UserRepo userRepo;

    @GetMapping("/dashboard")
    public String dashboard() {
        return "Greetings from admin/dashboard!";
    }

    @GetMapping("/owners/pending")
    public List<User> adminOwnersPending() {
        return userRepo.findByStatus(User.Status.STATUS_PENDING);
    }

    @PutMapping("/owners/:ownerId/approve")
    public String adminOwnersOwnerApprove() {
        return "Greetings from admin/owners/owner/approve!";
    }
}
