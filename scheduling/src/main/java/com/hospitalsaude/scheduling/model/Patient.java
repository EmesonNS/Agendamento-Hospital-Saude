package com.hospitalsaude.scheduling.model;

import com.hospitalsaude.scheduling.util.Gender;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tbl_paciente")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "nome_paciente", length = 100, nullable = false)
    private String name;

    @Column(name = "email_paciente", length = 100)
    private String email;

    @Column(name = "cpf", length = 20)
    private String cpf;
    private LocalDate dateBirth;
    private Gender gender;
    private String phone;
    private String bloodType;
    private String note;

}
