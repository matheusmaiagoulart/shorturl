package com.matheus.shorturl.domain.exceptions;

public class NotFoundException extends BusinessException {

    private static final String CODE = "NOT_FOUND";
    private static final String MESSAGE = "URL not found for short URL: ";

    public NotFoundException(String url) {
        super(CODE, MESSAGE.concat(url));
    }
}