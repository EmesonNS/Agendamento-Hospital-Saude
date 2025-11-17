package com.hospitalsaude.scheduling.dto;

import com.hospitalsaude.scheduling.util.Specialty;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

public record DoctorRequestDTO(

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

        @Positive(message = "O CRM deve ser um número positivo")
        int crm,

        @NotNull(message = "A especialidade não pode ser nula")
        Specialty specialty
) {
}
