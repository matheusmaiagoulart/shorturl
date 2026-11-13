package com.matheus.shorturl.application.service;

import java.time.Duration;
import java.time.LocalDateTime;

public class RedisTTLStrategy {

    public final static int DEFAULT_TTL_IN_HOURS = 24;
    public final static int DEFAULT_TTL_IN_MINUTES = DEFAULT_TTL_IN_HOURS * 60;

    public static int defineTTLInMinutes(LocalDateTime expireAt) {
        if (expireAt == null) return DEFAULT_TTL_IN_MINUTES;

        LocalDateTime now = LocalDateTime.now();

        if (expireAt.isBefore(now)) {
            throw new IllegalArgumentException("Expiration date cannot be in the past.");
        }


        if(!expireAt.isAfter(now.plusHours(DEFAULT_TTL_IN_HOURS))) {
            return Duration.between(now, expireAt).toMinutesPart();
        }

        return DEFAULT_TTL_IN_MINUTES;
    }
}
