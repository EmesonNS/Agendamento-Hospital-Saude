package com.hospitalsaude.scheduling.util;

import jakarta.persistence.AttributeConverter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DayWeekConverter implements AttributeConverter<List<DayWeek>, String> {
    @Override
    public String convertToDatabaseColumn(List<DayWeek> dayWeek) {
        if (dayWeek == null || dayWeek.isEmpty()){
            return null;
        }
        return dayWeek.stream()
                .map(DayWeek::name)
                .collect(Collectors.joining(","));
    }

    @Override
    public List<DayWeek> convertToEntityAttribute(String s) {
        if (s == null || s.isBlank()){
            return null;
        }
        return Arrays.stream(s.split(","))
                .map(DayWeek::valueOf)
                .collect(Collectors.toList());
    }
}
