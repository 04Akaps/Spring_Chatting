package com.example.demo.domain.auth.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.common.code.ErrorCode;
import com.example.demo.common.exception.CustomException;
import com.example.demo.domain.auth.model.request.CreateUserRequest;
import com.example.demo.domain.auth.model.request.LoginRequest;
import com.example.demo.domain.auth.model.request.VerifyTokenRequest;
import com.example.demo.domain.auth.model.request.VerifyUserRequest;
import com.example.demo.domain.auth.model.response.CreateUserResponse;
import com.example.demo.domain.auth.model.response.LoginResponse;
import com.example.demo.domain.auth.model.response.VerfiyTokenResponse;
import com.example.demo.domain.auth.model.response.VerifyUserResponse;
import com.example.demo.domain.repository.UserRepository;
import com.example.demo.domain.repository.types.User;
import com.example.demo.domain.repository.types.UserCrendentials;
import com.example.demo.security.Hasher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final Hasher hasher;
    
    @Transactional(transactionManager = "createUserTransactionManager")
    public CreateUserResponse createUser(CreateUserRequest request) {
        Optional<User> user =  userRepository.findByName(request.name());
        
        if (user.isPresent()) {
            log.error("Already Exist User {}", request.name());
            throw new CustomException(ErrorCode.USER_ALREADY_EXISTS);
        }

        try {
            User savedUser = userRepository.save(this.newUser(request.name(), request.password()));

            if (savedUser == null) {
                log.error("Saving User Failed {}", request.name());
                throw new CustomException(ErrorCode.USER_SAVE_FAILED);
            }   
        } catch (Exception e) {
            throw new CustomException(ErrorCode.USER_SAVE_FAILED, e.getMessage());
        }

        return new CreateUserResponse(request.name());
    }

    public LoginResponse login(LoginRequest request) {
        

        // TOken
        return new LoginResponse("test");
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


    private User newUser(String name, String password) {
        User newUser = User.builder().
        name(name).
        userCrendentials(this.newUserCrendentials(password)).
        build();
        
        return newUser;
    }

    private UserCrendentials newUserCrendentials(String password) {
        UserCrendentials cre = UserCrendentials.
        builder().
        hashed_password(hasher.getHashingValue(password)).
        build();
        return cre;
    }



}
