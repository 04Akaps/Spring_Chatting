package com.example.demo.domain.chat.controller;


import com.example.demo.domain.auth.model.request.CreateUserRequest;
import com.example.demo.domain.auth.model.request.LoginRequest;
import com.example.demo.domain.auth.model.response.CreateUserResponse;
import com.example.demo.domain.auth.model.response.LoginResponse;
import com.example.demo.domain.auth.service.AuthService;
import com.example.demo.domain.chat.model.response.ChatListResponse;
import com.example.demo.domain.chat.service.ChatService;

import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Chat API", description = "V1 : Chat관련 API를 담당합니다.")
@RestController
@RequestMapping("/api/v1/chat")
@RequiredArgsConstructor
public class ChatControllerV1 {
    
    private final ChatService chatService;

    @Operation(
        summary = "채팅 리스트를 가져옵니다.", 
        description = "가장 최근 10개의 채팅 리스트를 가져옵니다."
    )
    @GetMapping("/chat-list")
    public ChatListResponse chatList(
        @RequestParam("name") @Valid String name,
        @RequestParam("from") @Valid String from
    ) {
        return chatService.chatList(from, from);
    }

}
