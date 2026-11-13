package com.matheus.shorturl.adapter.input.rest;

import com.matheus.shorturl.adapter.input.DTOs.request.CreateShortURLRequest;
import com.matheus.shorturl.port.input.CreateShortURLUseCase;
import com.matheus.shorturl.port.input.GetOriginalUrlByShortUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class Controller {

    private final CreateShortURLUseCase createShortURLUseCase;
    private final GetOriginalUrlByShortUseCase getOriginalUrlByShortUseCase;

    @PostMapping("/shorten")
    public ResponseEntity<String> createShortURL(@RequestBody @Valid CreateShortURLRequest request) {
        return ResponseEntity.ok(createShortURLUseCase.execute(request));
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<String> getOriginalURL(@PathVariable String shortUrl) {

        String url = getOriginalUrlByShortUseCase.getOriginalUrl(shortUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("https://" + url));

        return ResponseEntity.status(302).headers(headers).build();
    }
}
