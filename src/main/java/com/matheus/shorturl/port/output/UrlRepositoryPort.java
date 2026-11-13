package com.matheus.shorturl.port.output;

import com.matheus.shorturl.domain.entity.URL;

public interface UrlRepositoryPort {

    void save(URL url);

    URL getOriginalUrlByShortUrl(String shortUrl);
}
