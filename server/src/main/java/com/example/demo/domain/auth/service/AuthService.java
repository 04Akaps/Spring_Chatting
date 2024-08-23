package com.example.demo.domain.auth.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.auth.model.request.CreateUserRequest;
import com.example.demo.domain.auth.model.request.VerifyTokenRequest;
import com.example.demo.domain.auth.model.request.VerifyUserRequest;
import com.example.demo.domain.auth.model.response.CreateUserResponse;
import com.example.demo.domain.auth.model.response.VerfiyTokenResponse;
import com.example.demo.domain.auth.model.response.VerifyUserResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    
    @Transactional(transactionManager = "createUserTransactionManager")
    public CreateUserResponse createUser(CreateUserRequest request) {
        // TODO
        
        

        return new CreateUserResponse(request.name());
    }

    @Transactional(transactionManager = "verifyUserTransactionManager")
    public VerifyUserResponse verifyUser(VerifyUserRequest request) {
        // TODO

        return new VerifyUserResponse(request.email());
    }

    public VerfiyTokenResponse verifyToken(VerifyTokenRequest request) {
        // TODO Redis

        return new VerfiyTokenResponse(true);
    }



}
