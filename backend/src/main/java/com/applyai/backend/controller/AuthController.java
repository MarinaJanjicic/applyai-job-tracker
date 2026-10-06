package com.applyai.backend.controller;

import com.applyai.backend.dto.auth.LoginRequest;
import com.applyai.backend.dto.auth.LoginResponse;
import com.applyai.backend.dto.auth.RegisterRequest;
import com.applyai.backend.dto.auth.RegisterResponse;
import com.applyai.backend.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public RegisterResponse register(@RequestBody @Valid RegisterRequest registerRequest){
        return userService.register(registerRequest);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Valid LoginRequest loginRequest){
        return userService.login(loginRequest);
    }
}
