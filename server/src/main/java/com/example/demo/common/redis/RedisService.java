package com.example.demo.common.redis;

import org.springframework.stereotype.Service;

import com.example.demo.common.code.ErrorCode;
import com.example.demo.common.exception.CustomException;

import java.time.Duration;
import java.util.Optional;

import org.springframework.data.redis.core.RedisTemplate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class RedisService {
    private final RedisTemplate<String, String> redisTemplate;

    // Config로 관리 가능
    private Duration defaultExpireTime = Duration.ofMinutes(5);

    public String getValue(String key) {
        return Optional.ofNullable(redisTemplate.opsForValue().get(key)).orElseThrow(
            () -> new CustomException(ErrorCode.REDIS_VALUE_NOT_FOUND)
            );
    }

     public void setData(String key, String value, Duration expireTime) {
        redisTemplate.opsForValue().set(key, value);
        redisTemplate.expire(key, expireTime);
    }

    public void setData(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
        redisTemplate.expire(key, this.defaultExpireTime);
    }
}
