package com.hospitalsaude.scheduling.dto;

import com.hospitalsaude.scheduling.util.Specialty;

public record DoctorResponseDTO(
        int id,
        String name,
        String email,
        String cpf,
        String phone,
        int crm,
        Specialty specialty
) {
}
