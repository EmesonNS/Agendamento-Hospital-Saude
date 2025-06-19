package com.hospitalsaude.scheduling.util;

import jakarta.persistence.AttributeConverter;

public class TypeAppointmentConverter implements AttributeConverter<TypeAppointment, String> {
    @Override
    public String convertToDatabaseColumn(TypeAppointment typeAppointment) {
        return typeAppointment != null ? typeAppointment.toString() : null;
    }

    @Override
    public TypeAppointment convertToEntityAttribute(String s) {
        return s != null ? TypeAppointment.fromString(s) : null;
    }
}
