package com.hospitalsaude.scheduling.dto;

import java.util.Map;

public record ErrorResponseDTO(
        String message,
        Map<String, String> errors
) {
}
