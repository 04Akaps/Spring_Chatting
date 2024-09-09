package com.example.demo.domain.user.service;


import org.springframework.stereotype.Service;

import com.example.demo.common.code.ErrorCode;
import com.example.demo.domain.repository.UserRepository;
import com.example.demo.domain.user.model.request.UserSearchRequest;
import com.example.demo.domain.user.model.response.UserSearchResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    
    public UserSearchResponse searchUser(String name, String user) {


        // 데이터베이스에서 패턴이 포함된 이름을 가진 유저를 검색합니다.
        List<String> names = userRepository.findNamesByPartialNameMatch(name, user);

        // 검색 결과와 함께 성공 응답을 반환합니다.
        return new UserSearchResponse(ErrorCode.SUCCESS, names);
    }
}
