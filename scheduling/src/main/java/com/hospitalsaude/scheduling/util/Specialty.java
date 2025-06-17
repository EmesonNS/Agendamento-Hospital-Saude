package com.hospitalsaude.scheduling.util;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Specialty {
    CLINICO_GERAL("Clinico Geral"),
    PEDIATRIA("Pediatria"),
    GINECOLOGIA("Ginecologia"),
    CARDIOLOGIA("Cardiologia"),
    ORTOPEDIA("Ortopedia"),
    DERMATOLOGIA("Dermatologia"),
    UROLOGIA("Urologia"),
    OFTALMOLOGIA("Oftalmologia"),
    NEUROLOGIA("Neurologia"),
    PSIQUIATRIA("Psiquiatria");

    private final String value;

    Specialty(String value){
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    @JsonCreator
    public static Specialty fromString(String value){
        for (Specialty specialty : Specialty.values()){
            if (specialty.value.equalsIgnoreCase(value.trim())){
                return specialty;
            }
        }
        throw new IllegalArgumentException("Especialidade inválida: " + value);
    }
}
