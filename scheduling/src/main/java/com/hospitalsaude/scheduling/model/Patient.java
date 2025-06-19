package com.hospitalsaude.scheduling.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tbl_paciente")
public class Patient extends User {

    @Column(name = "data_nascimento")
    private LocalDate dateBirth;

    @Column(name = "genero", length = 50)
    private String gender;

    @Column(name = "tipo_sanguineo", length = 10)
    private String bloodType;

    @Column(name = "observacao", length = 500)
    private String note;

    public LocalDate getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(LocalDate dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
