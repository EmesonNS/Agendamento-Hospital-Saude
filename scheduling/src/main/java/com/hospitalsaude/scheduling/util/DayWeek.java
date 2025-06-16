package com.hospitalsaude.scheduling.util;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum DayWeek {
    SEGUNDA("Segunda"),
    TERCA("Terça"),
    QUARTA("Quarta"),
    QUINTA("Quinta"),
    SEXTA("Sexta"),
    SABADO("Sabado"),
    DOMINGO("Domingo");

    private final String value;

    DayWeek(String value){
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    @JsonCreator
    public static DayWeek fromString(String value){
        for (DayWeek day : DayWeek.values()) {
            if (day.value.equalsIgnoreCase(value.trim())) {
                return day;
            }
        }
        throw new IllegalArgumentException("Dia da semana inválido: " + value);
    }

}
