package com.hospitalsaude.scheduling.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthenticationRequestDTO(
        @NotBlank(message = "O email não pode estar em branco")
        @Email(message = "O email fornecido é inválido")
        String email,

        @NotBlank(message = "A senha não pode estar em branco")
        String password
) {
}
