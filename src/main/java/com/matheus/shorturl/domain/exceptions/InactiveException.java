package com.matheus.shorturl.domain.exceptions;

public class InactiveException extends BusinessException {
    private static final String CODE = "INACTIVE";
    private static final String MESSAGE = "URL is inactive for short URL: ";

    public InactiveException(String url) {
        super(CODE, MESSAGE.concat(url));
    }
}
