package com.matheus.shorturl.infrastructure.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity(name = "url")
@Table(name = "urls")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class JpaURL {

    @Id
    private String shortUrl;
    private long ownerId;
    private String originalUrl;
    private boolean active;
    @Nullable private LocalDateTime expireAt;
    private LocalDateTime createdAt;

    public static JpaURL toEntity(String shortUrl, long ownerId, String originalUrl, boolean active, LocalDateTime expireAt, LocalDateTime createdAt) {
        return new JpaURL(shortUrl, ownerId, originalUrl, active, expireAt, createdAt);
    }
}
