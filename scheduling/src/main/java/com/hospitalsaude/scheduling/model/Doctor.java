package com.hospitalsaude.scheduling.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_medico")
public class Doctor extends User {

    @Column(name = "crm", nullable = false, unique = true)
    private int crm;

    @Column(name = "especialidade", nullable = false)
    private String specialty;

    @Column(name = "foto_medico")
    private String photoLink;

    public int getCrm() {
        return crm;
    }

    public void setCrm(int crm) {
        this.crm = crm;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }
}
