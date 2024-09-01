package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // 모든 경로에 대해 CORS를 허용
                .allowedOriginPatterns("http://localhost:*")  // 허용할 출처
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // 허용할 HTTP 메소드
                .allowedHeaders("*")  // 허용할 헤더
                .allowCredentials(true)  // 자격 증명을 허용
                .maxAge(3600);  // preflight 요청 캐시 시간
    }
}