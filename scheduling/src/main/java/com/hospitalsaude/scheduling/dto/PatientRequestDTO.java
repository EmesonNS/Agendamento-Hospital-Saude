package com.hospitalsaude.scheduling.dto;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record PatientRequestDTO(

        @NotBlank(message = "O nome não pode estar em branco")
        @Size(min = 3, message = "O nome deve ter no mínimo 3 caracteres")
        String name,

        @NotBlank(message = "O email não pode estar em branco")
        @Email(message = "O email fornecido é inválido")
        String email,

        @NotBlank(message = "A senha não pode estar em branco")
        @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
        String password,

        @NotBlank(message = "O CPF não pode estar em branco")
        @CPF(message = "O CPF fornecido é inválido")
        String cpf,


        String phone,

        @NotNull(message = "A data de nascimento não pode ser nulo")
        @Past(message = "A data de nascimento deve estar no passado")
        LocalDate dateBirth,

        String gender,
        String bloodType,
        String note
) {
}
