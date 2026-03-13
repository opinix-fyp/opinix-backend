package com.opinix.backend.controller;

import com.opinix.backend.dto.LoginRequest;
import com.opinix.backend.dto.LoginResponse;
import com.opinix.backend.dto.RegisterRequest;
import com.opinix.backend.dto.UserResponse;
import com.opinix.backend.service.AuthService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public UserResponse register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
