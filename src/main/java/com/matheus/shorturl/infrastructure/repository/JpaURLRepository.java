package com.matheus.shorturl.infrastructure.repository;

import com.matheus.shorturl.infrastructure.entity.JpaURL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaURLRepository extends JpaRepository<JpaURL, String> {

    @Query("SELECT u FROM url u WHERE u.shortUrl = :shortUrl")
    public JpaURL getOriginalUrlByShortUrl(String shortUrl);
}
