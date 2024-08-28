package com.example.demo.component;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.example.demo.domain.chat.model.Message;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class WssHandler extends TextWebSocketHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();
    
    @Override
    protected void handleTextMessage(WebSocketSession sess, TextMessage message) {
        try {
            // JSON 메시지를 Message 객체로 변환
            String payload = message.getPayload();
            Message msg = objectMapper.readValue(payload, Message.class);

            // 메시지 처리 로직
            System.out.println("Received message: " + msg);

            // 응답 메시지 (선택적)
            TextMessage response = new TextMessage("Message received");
            sess.sendMessage(response);
        } catch (Exception e) {
            // JSON 처리 중 오류 발생 시 처리
            log.error("Error processing JSON msg", e.getMessage());
            try {
                TextMessage errorResponse = new TextMessage("Error processing Message");
                sess.sendMessage(errorResponse);
            } catch (Exception ioException) {
                System.err.println("Error sending error msg: " + ioException.getMessage());
            }
        }
    }
}
