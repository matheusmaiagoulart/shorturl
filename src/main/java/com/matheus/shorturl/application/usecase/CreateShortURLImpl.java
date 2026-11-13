package com.matheus.shorturl.application.usecase;

import com.matheus.shorturl.adapter.input.DTOs.request.CreateShortURLRequest;
import com.matheus.shorturl.application.service.HashGenerator;
import com.matheus.shorturl.application.service.RedisTTLStrategy;
import com.matheus.shorturl.domain.entity.URL;
import com.matheus.shorturl.port.input.CreateShortURLUseCase;
import com.matheus.shorturl.port.output.RedisCachePort;
import com.matheus.shorturl.port.output.UrlRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CreateShortURLImpl implements CreateShortURLUseCase {

    private final HashGenerator hashGenerator;
    private final RedisCachePort redisCachePort;
    private final UrlRepositoryPort urlRepositoryPort;

    @Override
    @Transactional
    public String execute(CreateShortURLRequest request) {
        URL url = URL.create(hashGenerator.generate(), request.ownerId(), request.targetURL(), request.expireAt());

        urlRepositoryPort.save(url);
        redisCachePort.save(url.getShortUrl(), url.getOriginalUrl(), RedisTTLStrategy.defineTTLInMinutes(url.getExpireAt()));

        return url.getShortUrl();
    }
}
