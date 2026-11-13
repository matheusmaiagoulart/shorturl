package com.matheus.shorturl.adapter.output;

import com.matheus.shorturl.adapter.mapper.UrlMapper;
import com.matheus.shorturl.domain.entity.URL;
import com.matheus.shorturl.domain.exceptions.NotFoundException;
import com.matheus.shorturl.infrastructure.entity.JpaURL;
import com.matheus.shorturl.infrastructure.repository.JpaURLRepository;
import com.matheus.shorturl.port.output.UrlRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UrlRepositoryAdapter implements UrlRepositoryPort {

    private final JpaURLRepository jpaURLRepository;

    @Override
    public void save(URL url) {
        try {
            jpaURLRepository.saveAndFlush(UrlMapper.toEntity(url));
        } catch (DataIntegrityViolationException e) {
            log.error("Error saving URL: {}, URL already exists for this owner", e.getMessage());
            throw e;
        }
    }

    @Override
    public URL getOriginalUrlByShortUrl(String shortUrl) {
        JpaURL url = jpaURLRepository.getOriginalUrlByShortUrl(shortUrl);
        if (url == null) {
            throw new NotFoundException("URL not found for shortUrl: " + shortUrl);
        }

        return UrlMapper.toDomain(url);
    }
}
