package com.hospitalsaude.scheduling.dto;

import com.hospitalsaude.scheduling.util.StatusAppointment;

public record UpdateAppointmentStatusDTO(
        StatusAppointment status,
        String note
) {
}
