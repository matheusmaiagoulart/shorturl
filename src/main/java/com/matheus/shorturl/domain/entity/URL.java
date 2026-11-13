package com.matheus.shorturl.domain.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class URL {

    private String shortUrl;
    private long ownerId;
    private String originalUrl;
    private boolean active;
    private LocalDateTime expireAt;
    private LocalDateTime createdAt;

    public static URL create(String shortUrl, long ownerId, String originalUrl, LocalDateTime expireAt) {
            return URL.builder()
                .shortUrl(shortUrl)
                .ownerId(ownerId)
                .originalUrl(originalUrl)
                .expireAt(expireAt)
                .active(true)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public void disable() {
        this.active = false;
    }

    public boolean setExpireAt(LocalDateTime expireAt) {
        if (expireAt == null || expireAt.isBefore(LocalDateTime.now())) {
            return false;
        }

        this.expireAt = expireAt;
        return true;
    }

    public static URL reconstruct(String shortUrl, long ownerId, String originalUrl, Boolean active, LocalDateTime expireAt, LocalDateTime createdAt) {
        return URL.builder()
                .shortUrl(shortUrl)
                .ownerId(ownerId)
                .originalUrl(originalUrl)
                .expireAt(expireAt)
                .active(active)
                .createdAt(createdAt)
                .build();
    }
}
