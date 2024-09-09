package com.example.demo.domain.chat.service;


import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import com.example.demo.domain.chat.model.Message;
import com.example.demo.domain.chat.model.response.ChatListResponse;
import com.example.demo.domain.repository.ChatRepository;
import com.example.demo.domain.repository.UserRepository;
import com.example.demo.domain.repository.types.Chat;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// @Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {
    
    private final ChatRepository chatRepository;

    public ChatListResponse chatList(String from, String to) {

        List<Chat> chats = chatRepository.findTop10BySenderOrReceiverOrderByTIDDesc(from, to);

        // Entity -> DTO
        List<Message> res = chats.stream()
            .map(chat -> new Message(chat.getReceiver(), chat.getSender(), chat.getMessage()))
            .collect(Collectors.toList());


        return new ChatListResponse(res);
    }

    @Transactional(transactionManager = "creatChatTransactionManager")
    public void saveChatMessage(Message msg) { 
        Chat chat = this.newChat(msg);
        chatRepository.save(chat);
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
