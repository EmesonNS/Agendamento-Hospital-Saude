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

    @Column(name = "foto_medico")
    private String photoLink;

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

    public String getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }
}
