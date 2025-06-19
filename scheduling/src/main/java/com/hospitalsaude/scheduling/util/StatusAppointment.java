package com.hospitalsaude.scheduling.util;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum StatusAppointment {
    AGENDADA("Agendada"),
    CANCELADA("Cancelada"),
    REALIZADA("Realizada");

    private final String value;

    StatusAppointment(String value){
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    @JsonCreator
    public static StatusAppointment fromString(String value){
        for (StatusAppointment statusAppointment : StatusAppointment.values()){
            if (statusAppointment.value.equalsIgnoreCase(value.trim())) {
                return statusAppointment;
            }
        }
        throw new IllegalArgumentException("Status inválido: " + value);
    }
}
