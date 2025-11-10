package com.hospitalsaude.scheduling.dto;

import com.hospitalsaude.scheduling.util.Specialty;

public record DoctorRequestDTO(
        String name,
        String email,
        String password,
        String cpf,
        String phone,
        int crm,
        Specialty specialty
) {
}
