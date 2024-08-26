package com.example.demo.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import org.springframework.stereotype.Service;

@Service
public class Hasher {

    public String getHashingValue(String password) {
        try {
            // SHA-256 해시 알고리즘을 사용
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            
            // 해시 값을 Base64로 인코딩해서 문자열로 반환
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            // 예외 발생 시 기본값을 반환하거나 예외 메시지를 로그로 남기기
            throw new RuntimeException("SHA-256 algorithm not found", e);
        }
    }
}