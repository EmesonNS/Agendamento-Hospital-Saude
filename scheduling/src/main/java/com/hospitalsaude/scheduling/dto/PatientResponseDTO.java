package com.hospitalsaude.scheduling.dto;

import java.time.LocalDate;

public record PatientResponseDTO(
        int id,
        String name,
        String email,
        String cpf,
        String phone,
        LocalDate dateBirth,
        String gender,
        String bloodType,
        String note
) {
}
