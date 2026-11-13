package com.matheus.shorturl.application.service;

import io.seruco.encoding.base62.Base62;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

@Service
public class HashGenerator {

    private static final int CODE_BYTES = 6;
    private static final SecureRandom RANDOM = new SecureRandom();
    private static final Base62 BASE62 = Base62.createInstance();

    public String generate() {
        byte[] randomBytes = new byte[CODE_BYTES];
        RANDOM.nextBytes(randomBytes);

        return new String(BASE62.encode(randomBytes), StandardCharsets.UTF_8);
    }
}
