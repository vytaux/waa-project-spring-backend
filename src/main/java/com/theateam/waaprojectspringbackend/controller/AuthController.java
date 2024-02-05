package com.theateam.waaprojectspringbackend.controller;

import com.theateam.waaprojectspringbackend.entity.dto.request.LoginRequest;
import com.theateam.waaprojectspringbackend.entity.dto.request.RefreshTokenRequest;
import com.theateam.waaprojectspringbackend.entity.dto.request.RegisterRequest;
import com.theateam.waaprojectspringbackend.entity.dto.response.LoginResponse;
import com.theateam.waaprojectspringbackend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@CrossOrigin
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest registerRequest) {
        authService.register(registerRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = authService.login(loginRequest);
        return ResponseEntity.ok().body(loginResponse);
    }

    @PostMapping("/refresh-token")
    public LoginResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest){
        return authService.refreshToken(refreshTokenRequest);
    }
}