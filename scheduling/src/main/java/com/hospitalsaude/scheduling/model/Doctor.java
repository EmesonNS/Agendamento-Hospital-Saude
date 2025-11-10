package com.hospitalsaude.scheduling.model;

import com.hospitalsaude.scheduling.util.Specialty;
import com.hospitalsaude.scheduling.util.SpecialtyConverter;
import jakarta.persistence.*;

@Entity
@Table(name = "tbl_medico")
public class Doctor extends User {

    @Column(name = "crm", nullable = false, unique = true)
    private int crm;

    @Convert(converter = SpecialtyConverter.class)
    @Column(name = "especialidade", nullable = false)
    private Specialty specialty;

    public int getCrm() {
        return crm;
    }

    public void setCrm(int crm) {
        this.crm = crm;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }
}
