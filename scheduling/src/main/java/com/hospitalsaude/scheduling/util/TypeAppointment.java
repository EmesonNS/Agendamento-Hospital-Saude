package com.hospitalsaude.scheduling.util;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum TypeAppointment {
    PRESENCIAL("Presencial"),
    TELECONSULTA("Teleconsulta");

    private final String value;

    TypeAppointment(String value){
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    @JsonCreator
    public static TypeAppointment fromString(String value){
        for (TypeAppointment typeAppointment : TypeAppointment.values()){
            if ((typeAppointment.value.equalsIgnoreCase(value.trim()))){
                return typeAppointment;
            }
        }
        throw new IllegalArgumentException("Tipo de consulta inválida: " + value);
    }
}
