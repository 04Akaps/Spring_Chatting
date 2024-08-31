package com.example.demo.component;

import org.springframework.stereotype.Component;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.example.demo.domain.chat.model.Message;
import com.example.demo.domain.chat.service.ChatService;
import com.example.demo.domain.repository.types.Chat;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;
import java.util.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class WssHandler extends TextWebSocketHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final ChatService chatService;

    private final Map<String, Map<String, WebSocketSession>> userSessions = new HashMap<>();

    @Override
    protected void handleTextMessage(WebSocketSession sess, TextMessage message) {
        try {
            // JSON 메시지를 Message 객체로 변환
            String payload = message.getPayload();
            Message msg = objectMapper.readValue(payload, Message.class);

            String sender = msg.getFrom();
            String to = msg.getTo();

            // if (!wssUsersExisted(sender, to)) {
            //     log.error("User Not Existed sender: {}, to: {}", sender, to);
            // }
            
            // TODO -> Save 동작 버그 수정
            chatService.saveChatMessage(msg);
        
            // 저장 또는 Update
            userSessions.computeIfAbsent(sender, k -> new HashMap<>()).put(to, sess);
            
            WebSocketSession toSession = userSessions.get(sender).get(to);

            if (toSession != null && toSession.isOpen()) {
                toSession.sendMessage(new TextMessage(payload));
            } else {
                log.error("Failed to send message via WebSocket, rolling back DB operation.");
            }

        } catch (Exception e) {
            // JSON 처리 중 오류 발생 시 처리
            log.error("Error processing JSON msg : {}", e.getMessage());
            try {
                TextMessage errorResponse = new TextMessage("Error processing Message");
                sess.sendMessage(errorResponse);
            } catch (Exception ioException) {
                log.error("Error sending error msg: {}", e.getMessage());

            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // 연결이 종료된 경우 세션을 Map에서 제거
        userSessions.values().forEach(map -> map.values().removeIf(sess -> sess.equals(session)));
        log.info("Session closed: {}", session.getId());
        super.afterConnectionClosed(session, status);
    }

    
}
