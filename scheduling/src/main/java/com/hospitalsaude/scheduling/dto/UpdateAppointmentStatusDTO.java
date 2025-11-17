package com.hospitalsaude.scheduling.dto;

import com.hospitalsaude.scheduling.util.StatusAppointment;
import jakarta.validation.constraints.NotNull;

public record UpdateAppointmentStatusDTO(

        @NotNull(message = "O status não pode ser nulo")
        StatusAppointment status,

        String note
) {
}
