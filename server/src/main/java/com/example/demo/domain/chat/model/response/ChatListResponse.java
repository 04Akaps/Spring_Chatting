package com.example.demo.domain.chat.model.response;

import com.example.demo.domain.chat.model.Message;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Chatting List")
public record ChatListResponse (
    @Schema(description = "Message []")
    List<Message> result
) {}
