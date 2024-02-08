package com.theateam.waaprojectspringbackend.controller;

import com.theateam.waaprojectspringbackend.entity.RoleType;
import com.theateam.waaprojectspringbackend.entity.User;
import com.theateam.waaprojectspringbackend.entity.UserStatus;
import com.theateam.waaprojectspringbackend.repository.UserRepo;
import com.theateam.waaprojectspringbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.hibernate.usertype.UserType;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@CrossOrigin
@RequiredArgsConstructor
public class AdminController {

    private final UserRepo userRepo;
    private final UserService userService;

    @GetMapping("/owners/pending")
    public List<User> adminOwnersPending() {
        return userRepo.findByStatusAndRoleType(
                UserStatus.STATUS_WAITING_FOR_APPROVAL,
                RoleType.OWNER
        );
    }

    @PutMapping("/owners/{ownerId}/approve")
    public void adminOwnersOwnerApprove(@PathVariable Long ownerId) {
        userService.approveUser(ownerId);
    }
}
