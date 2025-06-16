package com.hospitalsaude.scheduling.util;

public enum Gender {
    MASCULINO("Masculino"),
    FEMININO("Feminino");

    private final String value;

    Gender(String value){
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public static Gender fromString(String value){
        for (Gender gender : Gender.values()){
            if (gender.value.equalsIgnoreCase(value.trim())){
                return gender;
            }
        }
        throw new IllegalArgumentException("Gênero inválido: " + value);
    }
}
