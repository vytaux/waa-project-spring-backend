package com.theateam.waaprojectspringbackend.util;

import com.theateam.waaprojectspringbackend.entity.User;
import com.theateam.waaprojectspringbackend.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
@RequiredArgsConstructor
public class AuthUtil {

    private final UserRepo repo;

    public UserDetails getUserDetails() {
        return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public User getUser() {
        return repo.findByEmail(getUserDetails().getUsername()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Logged in User Not Found"));
    }

}
