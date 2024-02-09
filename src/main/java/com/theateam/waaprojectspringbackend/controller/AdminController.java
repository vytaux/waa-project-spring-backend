package com.theateam.waaprojectspringbackend.controller;

import com.theateam.waaprojectspringbackend.entity.User;
import com.theateam.waaprojectspringbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@CrossOrigin
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;

    @GetMapping("/owners/pending")
    public List<User> adminOwnersPending() {
        return userService.findPendingOwners();
    }

    @PutMapping("/owners/{ownerId}/approve")
    public void adminOwnersOwnerApprove(@PathVariable Long ownerId) {
        userService.approveUser(ownerId);
    }
}
