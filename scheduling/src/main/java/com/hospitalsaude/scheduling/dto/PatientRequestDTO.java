package com.hospitalsaude.scheduling.dto;

import java.time.LocalDate;

public record PatientRequestDTO(
        String name,
        String email,
        String password,
        String cpf,
        String phone,
        LocalDate dateBirth,
        String gender,
        String bloodType,
        String note
) {
}
