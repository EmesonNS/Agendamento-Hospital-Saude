package com.hospitalsaude.scheduling.util;

import jakarta.persistence.AttributeConverter;

public class DayWeekConverter implements AttributeConverter<DayWeek, String> {
    @Override
    public String convertToDatabaseColumn(DayWeek dayWeek) {
        return dayWeek != null ? dayWeek.toString() : null;
    }

    @Override
    public DayWeek convertToEntityAttribute(String s) {
        return s != null ? DayWeek.fromString(s) : null;
    }
}
