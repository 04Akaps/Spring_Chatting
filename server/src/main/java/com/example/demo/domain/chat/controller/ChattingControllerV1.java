package com.example.demo.domain.chat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "Chatting Web Socket", description = "V1: Chatting WSS 통신을 담당합니다.")
@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/wss/v1/chat")
public class ChattingControllerV1 {

    //   public void message(ChatMessage message, @Header("token") String token) {
    //     String nickname = jwtTokenProvider.getUserNameFromJwt(token);
    //     // 로그인 회원 정보로 대화명 설정
    //     message.setSender(nickname);
    //     // 채팅방 인원수 세팅
    //     message.setUserCount(chatRoomRepository.getUserCount(message.getRoomId()));
    //     // Websocket에 발행된 메시지를 redis로 발행(publish)
    //     chatService.sendChatMessage(message);
    // }
}
