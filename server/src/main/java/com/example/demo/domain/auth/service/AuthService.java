package com.example.demo.domain.auth.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.common.code.ErrorCode;
import com.example.demo.common.constrants.Constants;
import com.example.demo.common.exception.CustomException;
import com.example.demo.common.redis.RedisService;
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
import com.example.demo.security.JWTProvider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

import javax.sound.midi.SysexMessage;

import java.sql.Timestamp;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RedisService redisService;
    private final Hasher hasher;
    
    @Transactional(transactionManager = "createUserTransactionManager")
    public CreateUserResponse createUser(CreateUserRequest request) {
        Optional<User> user =  userRepository.findByName(request.name());
        
        if (user.isPresent()) {
            log.error("Already Exist User {}", request.name());
            throw new CustomException(ErrorCode.USER_ALREADY_EXISTS);
        }

        try {
            // TODO -> user, crendetionals 분리

            User newUser = this.newUser(request.name());
            UserCrendentials userCrendentials = this.newUserCrendentials(request.password(), newUser);
            newUser.setCredentials(userCrendentials);

            User savedUser = userRepository.save(newUser);

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

        Optional<User> user =  userRepository.findByName(request.name());
        
        if (!user.isPresent()) {
            log.error("Already Exist User {}", request.name());
            throw new CustomException(ErrorCode.USER_ALREADY_EXISTS);
        }

        String hasherPassword = user
        .map(u -> {
            String passwordHash = hasher.getHashingValue(request.password());

            // 해시 비밀번호 비교
            if (!u.getUserCrendentials().getHashed_password().equals(passwordHash)) {
                log.error("Password Check Failed : {}", request.name());
                throw new CustomException(ErrorCode.LOGIN_PASSWORD_FAILED);
            }
            return passwordHash; // return the valid hash
        })
        .orElseThrow(() -> {
            log.error("User not found: {}", request.name());
            return new CustomException(ErrorCode.LOGIN_PASSWORD_FAILED);
        });

        String key = this.userRedisKey(request.name());
        String token = JWTProvider.createToken(request.name());

        try {
            // TODO Token Data
            redisService.setData(key, token);   
        } catch (Exception e) {
            log.error("Redis Save Failed : {}", e.getMessage());
            throw new CustomException(ErrorCode.REDIS_SAVE_FAILED);
        }
    
        return new LoginResponse(token);
    }

    private String userRedisKey(String name)  {
        String baseKey = Constants.AUTH_CACHE_KEY + "-" + name;
        return hasher.getHashingValue(baseKey);
    }

    private User newUser(String name) {
        User newUser = User.builder().
        name(name).
        created_at(new Timestamp(System.currentTimeMillis())).
        build();
        
        return newUser;
    }

    private UserCrendentials newUserCrendentials(String password, User user) {
        String hashValue = hasher.getHashingValue(password);

        UserCrendentials cre = UserCrendentials.
        builder().
        user(user).
        hashed_password(hashValue).
        build();
        return cre;
    }

}
