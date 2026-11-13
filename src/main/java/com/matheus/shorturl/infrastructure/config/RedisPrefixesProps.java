package com.matheus.shorturl.infrastructure.config;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Component
@ConfigurationProperties(prefix = "spring.data.redis.prefixes")
public class RedisPrefixesProps {

    private String shortUrl;

    public String getShortUrlPrefix() {
        return shortUrl;
    }
}
