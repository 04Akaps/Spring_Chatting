package com.example.demo.domain.chat.service;


import java.sql.Timestamp;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import com.example.demo.domain.chat.model.Message;
import com.example.demo.domain.repository.ChatRepository;
import com.example.demo.domain.repository.UserRepository;
import com.example.demo.domain.repository.types.Chat;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {
    
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;

    public void saveChatMessage(Message msg) {
        chatRepository.save(this.newChat(msg));       
    }

    private boolean userExisted(String sender, String to) {
        return userRepository.existsByName(sender) && userRepository.existsByName(to);
    }

    private Chat newChat(Message msg) {
        return Chat.
        builder().
        sender(msg.getFrom()).
        receiver(msg.getTo()).
        message(msg.getMessage()).
        created_at(new Timestamp(System.currentTimeMillis())).
        build();
    }
}
