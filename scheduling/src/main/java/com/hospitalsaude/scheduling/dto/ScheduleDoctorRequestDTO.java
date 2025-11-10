package com.hospitalsaude.scheduling.dto;

import com.hospitalsaude.scheduling.util.DayWeek;

import java.time.LocalTime;
import java.util.List;

public record ScheduleDoctorRequestDTO(
        int doctorId,
        List<DayWeek> dayWeek,
        LocalTime startTime,
        LocalTime endTime
) {
}
