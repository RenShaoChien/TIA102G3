package com.controllers.mark;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    // 存儲數據
    public void save(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    // 獲取數據
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    // 刪除數據
    public void delete(String key) {
        redisTemplate.delete(key);
    }
}

