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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepo roleRepo;

    public ResponseEntity<String> register(RegisterRequest registerRequest) {
        User user = new User();
        var userEmail = registerRequest.getEmail();

        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setEmail(userEmail);
        user.setStatus(UserStatus.STATUS_WAITING_FOR_APPROVAL);

        if (userRepo.existsByEmail(userEmail)) {
            return ResponseEntity.badRequest().body(String.format("User %s already exists", userEmail));
        }

        // TODO don't allow save ROLE_ADMIN!!!
        Role role = roleRepo.findByRoleType(registerRequest.getRole());
        user.getRoles().add(role);

        userRepo.save(user);

        return ResponseEntity.ok("User Save");
    }

    public LoginResponse login(LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

            final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());

            final String accessToken = jwtUtil.generateToken(userDetails);
            final String refreshToken = jwtUtil.generateRefreshToken(loginRequest.getEmail());
            var loginResponse = new LoginResponse(accessToken, refreshToken);
            return loginResponse;
        } catch (AuthenticationException ex) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Invalid email or password");
        }
    }

    public LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        boolean isRefreshTokenValid = jwtUtil.validateToken(refreshTokenRequest.getRefreshToken());
        if (isRefreshTokenValid) {
            // TODO (check the expiration of the accessToken when request sent, if the is recent according to
            //  issue Date, then accept the renewal)
            var isAccessTokenExpired = jwtUtil.isTokenExpired(refreshTokenRequest.getAccessToken());
            if (isAccessTokenExpired) System.out.println("ACCESS TOKEN IS EXPIRED"); // TODO Renew is this case
            else System.out.println("ACCESS TOKEN IS NOT EXPIRED");
            final String accessToken = jwtUtil.doGenerateToken(jwtUtil.getSubject(refreshTokenRequest.getRefreshToken()));
            LoginResponse loginResponse = new LoginResponse(accessToken, refreshTokenRequest.getRefreshToken());
            // TODO (OPTIONAL) When to renew the refresh token?
            return loginResponse;
        }
        return new LoginResponse();
    }
}
