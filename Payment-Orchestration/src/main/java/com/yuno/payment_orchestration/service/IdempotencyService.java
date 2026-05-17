package com.yuno.payment_orchestration.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class IdempotencyService {

    private final RedisTemplate<String, Object> redisTemplate;

    public boolean isDuplicate(String key) {
        return redisTemplate.hasKey(key);
    }

    public void save(String key, Object response) {
        redisTemplate.opsForValue().set(key, response, 10, TimeUnit.MINUTES);
    }

    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}