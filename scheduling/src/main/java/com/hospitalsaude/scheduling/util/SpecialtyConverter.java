package com.hospitalsaude.scheduling.util;

import jakarta.persistence.AttributeConverter;

public class SpecialtyConverter implements AttributeConverter<Specialty, String> {
    @Override
    public String convertToDatabaseColumn(Specialty specialty) {
        return specialty != null ? specialty.toString() : null;
    }

    @Override
    public Specialty convertToEntityAttribute(String s) {
        return s != null ? Specialty.fromString(s) : null;
    }
}
