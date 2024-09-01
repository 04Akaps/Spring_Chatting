package com.example.demo.domain.user.controller;

import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;

import com.example.demo.domain.user.service.UserService;

import com.example.demo.domain.user.model.request.*;
import com.example.demo.domain.user.model.response.*;

@Tag(name = "User API", description = "V1 : User 관련 API를 담당합니다.")
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserControllerV1 {

    private final UserService userService;
    
    @Operation(
        summary = "User Name List Search",
        description = "User Name을 기반으로 Like 검색을 실행합니다."
    )
    @GetMapping("/search/{name}")
    public UserSearchResponse searchUser(
        @PathVariable("name") String name
    ) {
        return userService.searchUser(name);
    }

}
