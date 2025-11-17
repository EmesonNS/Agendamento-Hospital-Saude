package com.hospitalsaude.scheduling.util;

import jakarta.persistence.AttributeConverter;

public class StatusAppointmentConverter implements AttributeConverter<StatusAppointment, String> {
    @Override
    public String convertToDatabaseColumn(StatusAppointment statusAppointment) {
        return statusAppointment != null ? statusAppointment.toString() : null;
    }

    @Override
    public StatusAppointment convertToEntityAttribute(String s) {
        return s != null ? StatusAppointment.fromString(s) : null;
    }
}
