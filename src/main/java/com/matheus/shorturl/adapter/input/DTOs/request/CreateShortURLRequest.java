package com.matheus.shorturl.adapter.input.DTOs.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CreateShortURLRequest(
        @NotNull(message = "Owner ID is required")
        long ownerId,
        @NotNull(message = "Target URL is required")
        String targetURL,
        @Future(message = "Expiration date must be in the future")
        @Nullable
        LocalDateTime expireAt
) {
}
