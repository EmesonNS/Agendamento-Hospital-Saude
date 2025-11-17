package com.hospitalsaude.scheduling.dto;

import java.time.LocalDateTime;
import java.util.Map;

public record ErrorResponseDTO(
        String message,
        Map<String, Object> errors,
        LocalDateTime timestamp
) {
    public ErrorResponseDTO(String message, Map<String, Object> errors) {
        this(message, errors, LocalDateTime.now());
    }
}
