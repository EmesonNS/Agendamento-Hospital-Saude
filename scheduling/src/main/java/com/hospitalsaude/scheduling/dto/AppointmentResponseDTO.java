package com.hospitalsaude.scheduling.dto;

import com.hospitalsaude.scheduling.util.StatusAppointment;
import com.hospitalsaude.scheduling.util.TypeAppointment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record AppointmentResponseDTO(
        int id,
        DoctorResponseDTO doctor,
        PatientResponseDTO patient,
        LocalDateTime dateAppointment,
        LocalDate date,
        LocalTime time,
        StatusAppointment status,
        TypeAppointment type,
        String note
) {
}
