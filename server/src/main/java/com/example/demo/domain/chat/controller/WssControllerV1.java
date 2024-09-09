package com.example.demo.domain.chat.controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.example.demo.domain.chat.model.Message;
import com.example.demo.domain.chat.service.ChatService;
import com.example.demo.domain.repository.ChatRepository;
import com.example.demo.domain.repository.types.Chat;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.sql.Timestamp;

@RequiredArgsConstructor
@Controller
@Slf4j
public class WssControllerV1 {

    private final ChatService chatService;

    @MessageMapping("/chat/message/{from}")
    @SendTo("/sub/chat")
    public Message sendMessage(
        @DestinationVariable String from,
        Message msg
    ) { 
        log.info("Message Sended -> From: {}, to: {}, message: {}", from, msg.getTo(), msg.getMessage());
        chatService.saveChatMessage(msg);
        return msg; 
    }
}
