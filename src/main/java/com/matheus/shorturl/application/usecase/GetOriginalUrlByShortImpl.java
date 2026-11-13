package com.matheus.shorturl.application.usecase;

import com.matheus.shorturl.application.service.RedisTTLStrategy;
import com.matheus.shorturl.domain.entity.URL;
import com.matheus.shorturl.domain.exceptions.InactiveException;
import com.matheus.shorturl.domain.exceptions.NotFoundException;
import com.matheus.shorturl.port.input.GetOriginalUrlByShortUseCase;
import com.matheus.shorturl.port.output.RedisCachePort;
import com.matheus.shorturl.port.output.UrlRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetOriginalUrlByShortImpl implements GetOriginalUrlByShortUseCase {

    private final RedisCachePort redisCachePort;
    private final UrlRepositoryPort urlRepositoryPort;

    @Override
    public String getOriginalUrl(String shortUrl) {

        String originalUrlCached = redisCachePort.get(shortUrl, String.class).orElse(null);
        if (originalUrlCached != null) {
            return originalUrlCached;
        }

        URL url = urlRepositoryPort.getOriginalUrlByShortUrl(shortUrl);

        if (url == null) {
            throw new NotFoundException(shortUrl);
        }

        if (!url.isActive()) {
            throw new InactiveException(shortUrl);
        }

        redisCachePort.save(shortUrl, url.getOriginalUrl(), RedisTTLStrategy.defineTTLInMinutes(url.getExpireAt()));

        return url.getOriginalUrl();
    }
}
