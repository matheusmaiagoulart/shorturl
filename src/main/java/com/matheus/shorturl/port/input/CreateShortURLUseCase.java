package com.matheus.shorturl.port.input;

import com.matheus.shorturl.adapter.input.DTOs.request.CreateShortURLRequest;

public interface CreateShortURLUseCase {
    String execute(CreateShortURLRequest request);
}
