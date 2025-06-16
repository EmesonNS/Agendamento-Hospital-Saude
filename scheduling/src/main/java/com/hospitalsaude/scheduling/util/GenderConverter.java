package com.hospitalsaude.scheduling.util;

import jakarta.persistence.AttributeConverter;

public class GenderConverter implements AttributeConverter<Gender, String> {

    @Override
    public String convertToDatabaseColumn(Gender gender) {
        return gender != null ? gender.toString() : null;
    }

    @Override
    public Gender convertToEntityAttribute(String s) {
        return s != null ? Gender.fromString(s) : null;
    }
}
