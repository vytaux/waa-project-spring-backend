package com.theateam.waaprojectspringbackend.service;

import com.theateam.waaprojectspringbackend.entity.Role;
import com.theateam.waaprojectspringbackend.entity.User;
import com.theateam.waaprojectspringbackend.entity.UserStatus;
import com.theateam.waaprojectspringbackend.entity.dto.request.LoginRequest;
import com.theateam.waaprojectspringbackend.entity.dto.request.RefreshTokenRequest;
import com.theateam.waaprojectspringbackend.entity.dto.request.RegisterRequest;
import com.theateam.waaprojectspringbackend.entity.dto.response.LoginResponse;
import com.theateam.waaprojectspringbackend.repository.RoleRepo;
import com.theateam.waaprojectspringbackend.repository.UserRepo;
import com.theateam.waaprojectspringbackend.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepo userRepo;

    public void approveUser(Long userId) {
        User user = userRepo.findById(userId).orElseThrow();
        user.setStatus(UserStatus.STATUS_APPROVED);
        userRepo.save(user);
    }
}
