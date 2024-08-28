package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;

@Configuration
@EnableWebSocket
public class WssConfig implements WebSocketConfigurer {

    // private final 

    // // @Bean
    // // public ServeletServerConta

    //  // 요청은 핸들러로 라우트 되고
    // // beforeHandshake메소드에서 헤더 중 필요한 값을 가져와 true값 반환하면 Upgrade 헤더와 함께 101 Switching Protocols 상태 코드를 포함한 응답 반환
    
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(null, "/ws/v1/chat").setAllowedOrigins("*");
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
