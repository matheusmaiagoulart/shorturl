package com.matheus.shorturl.adapter.output;

import com.matheus.shorturl.infrastructure.config.RedisPrefixesProps;
import com.matheus.shorturl.port.output.RedisCachePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class RedisCacheAdapter implements RedisCachePort {

    private final ObjectMapper objectMapper;
    private final RedisTemplate<String, String> redisTemplate;


    @Override
    public <T> Optional<T> get(String key, Class<T> desserializationClass) {
        log.info("Fetching from Redis cache with key: {}", key);
        Object result = null;
        try {
            result = redisTemplate.opsForValue().get(key);
        } catch (RedisConnectionFailureException e) {
            log.error("Redis connection failure while fetching key: {} cause: {}", key, e.getMessage());
        }

        if (result == null) {
            log.info("No cache found for key: {}", key);
            return Optional.empty();
        }

        try {
            T response = objectMapper.readValue(result.toString(), desserializationClass);
            log.info("Returning cached value for key: {}", key);
            return Optional.of(response);
        } catch (Exception e) {
            log.error("Error deserializing Redis cache value for key: {}", key, e);
            return Optional.empty();
        }
    }

    @Override
    public void save(String key, Object value, int cacheDurationHours) {
        try {
            String jsonValue = objectMapper.writeValueAsString(value);
            redisTemplate.opsForValue().set(key, jsonValue, cacheDurationHours, TimeUnit.MINUTES);
            log.info("Saved to Redis cache with key: {}", key);
        } catch (Exception e) {
            log.error("Error saving to Redis cache with key: {}", key, e);
        }
    }
}
