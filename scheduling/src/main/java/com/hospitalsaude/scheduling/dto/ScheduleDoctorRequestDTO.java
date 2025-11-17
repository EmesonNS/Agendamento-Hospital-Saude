package com.hospitalsaude.scheduling.dto;

import com.hospitalsaude.scheduling.util.DayWeek;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalTime;
import java.util.List;

public record ScheduleDoctorRequestDTO(

        @Positive(message = "O ID do médico deve ser positivo")
        int doctorId,

        @NotEmpty(message = "A lista de dias da semana não pode ser vazia")
        List<DayWeek> dayWeek,

        @NotNull(message = "A hora de início não pode ser nula")
        LocalTime startTime,

        @NotNull(message = "A hora de fim não pode ser nula")
        LocalTime endTime
) {
}
