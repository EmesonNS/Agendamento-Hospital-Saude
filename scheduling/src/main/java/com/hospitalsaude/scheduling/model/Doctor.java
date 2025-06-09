package com.hospitalsaude.scheduling.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_medico")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_medico", nullable = false)
    private int id;

    @Column(name = "nome_medico", length = 50, nullable = false)
    private String name;

    @Column(name = "crm", nullable = false, unique = true)
    private int crm;

    @Column(name = "especialidade", nullable = false)
    private String specialty;

    @Column(name = "telefone_medico")
    private String phone;

    @Column(name = "email_medico")
    private String email;

    @Column(name = "foto_medico")
    private String photoLink;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCrm() {
        return crm;
    }

    public void setCrm(int crm) {
        this.crm = crm;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }
}
