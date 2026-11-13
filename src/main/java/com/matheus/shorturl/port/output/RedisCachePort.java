package com.matheus.shorturl.port.output;

import java.util.Optional;

public interface RedisCachePort {

    <T> Optional<T> get(String key, Class<T> desserializationClass);

    void save(String key, Object value, int cacheDurationMinutes);
}
