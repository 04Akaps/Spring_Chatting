package com.example.demo.domain.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.auth.model.request.CreateUserRequest;
import com.example.demo.domain.auth.model.request.VerifyTokenRequest;
import com.example.demo.domain.auth.model.request.VerifyUserRequest;
import com.example.demo.domain.auth.model.response.CreateUserResponse;
import com.example.demo.domain.auth.model.response.VerfiyTokenResponse;
import com.example.demo.domain.auth.model.response.VerifyUserResponse;
import com.example.demo.domain.auth.service.AuthService;

import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Auth API", description = "V1 : Auth 관련 API를 담당합니다.")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthControllerV1 {
    private final AuthService authService;

    @Operation(
        summary = "새로운 유저 생성", 
        description = "새로운 유저를 생성합니다."
    )
    @PostMapping("/create-user")
    public CreateUserResponse createUser(
        @RequestBody @Valid CreateUserRequest request
    ) {
        return authService.createUser(request);
    }
    
    @Operation(
        summary = "Token을 기반으로 인증합니다.", 
        description = "Token 인증"
    )
    @GetMapping("/verify-token/{token}")
    public VerfiyTokenResponse verifyLogin(
        @RequestParam @Valid VerifyTokenRequest request
    ) {
        return authService.verifyToken(request);
    }

    @Operation(
        summary = "User를 인증합니다.", 
        description = "User 인증"
    )
    @GetMapping("/verify-user/{user}")
    public VerifyUserResponse verifyUser(
        @RequestParam @Valid VerifyUserRequest request
    ) {
        return authService.verifyUser(request);
    }
}
