package com.hospitalsaude.scheduling.dto;

import com.hospitalsaude.scheduling.util.TypeAppointment;

import java.time.LocalDate;
import java.time.LocalTime;

public record AppointmentRequestDTO(
        int doctorId,
        int patientId,
        LocalDate date,
        LocalTime time,
        TypeAppointment type,
        String note
) {
}
