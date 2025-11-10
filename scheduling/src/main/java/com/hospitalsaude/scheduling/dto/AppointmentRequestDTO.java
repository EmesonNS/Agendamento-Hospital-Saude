package com.hospitalsaude.scheduling.dto;

import com.hospitalsaude.scheduling.util.TypeAppointment;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;
import java.time.LocalTime;

public record AppointmentRequestDTO(

        @Positive(message = "O ID do médido deve ser positivo")
        int doctorId,

        @Positive(message = "O ID do paciente deve ser positivo")
        int patientId,

        @NotNull(message = "A data da consulta não pode ser nula")
        @Future(message = "A data da consulta deve ser no futuro")
        LocalDate date,

        @NotNull(message = "A hora da consulta não pode ser nula")
        LocalTime time,

        @NotNull(message = "O tipo da consulta não pode ser nulo")
        TypeAppointment type,

        String note
) {
}
