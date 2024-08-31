package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;

import com.example.demo.component.WssHandler;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class WssConfig implements WebSocketConfigurer {

    private final WssHandler wssHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(wssHandler, "/ws/v1/chat").setAllowedOrigins("*");
        // registry.addHandler(socketHandler, "/wss/chat")
        //         .addInterceptors(new HttpSessionHandshakeInterceptor(), new CustomHandshakeInterceptor())
        //         .setAllowedOrigins("https:/~~/chatting("서버url");
    }

    //만약 CORS때문에 origin에서 403에러가 뜬다면
    //String[] origins = {"https://www.url1.com", "https://m.url2.com", "https://url3.com"}; 이렇게 여러개의 origin들을 setAllowedOrigins에 대입해도 됨

    @Bean
    public ServletServerContainerFactoryBean createWebSocketContainer() {
        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
        container.setMaxBinaryMessageBufferSize(500000);
        container.setMaxTextMessageBufferSize(500000);
        return container;
    }
}
