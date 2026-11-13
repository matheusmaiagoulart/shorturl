package com.matheus.shorturl.adapter.mapper;


import com.matheus.shorturl.domain.entity.URL;
import com.matheus.shorturl.infrastructure.entity.JpaURL;

public class UrlMapper {

    public static JpaURL toEntity(com.matheus.shorturl.domain.entity.URL url) {
        return JpaURL.toEntity(
                url.getShortUrl(),
                url.getOwnerId(),
                url.getOriginalUrl(),
                url.isActive(),
                url.getExpireAt(),
                url.getCreatedAt());
    }

    public static URL toDomain(JpaURL jpaURL) {
        return URL.reconstruct(
                jpaURL.getShortUrl(),
                jpaURL.getOwnerId(),
                jpaURL.getOriginalUrl(),
                jpaURL.isActive(),
                jpaURL.getExpireAt(),
                jpaURL.getCreatedAt());
    }
}
