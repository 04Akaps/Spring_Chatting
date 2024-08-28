package com.example.demo.domain.chat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    // 메시지 타입 : 입장, 퇴장, 채팅
    public enum MessageType {
        ENTER, EXIT, CHAT
    }

    private MessageType type; // 메시지 타입
    private String to; // 방번호
    private String sender; // 메시지 보낸사람
    private String message; // 메시지
}
