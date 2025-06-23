package com.hospitalsaude.scheduling.model;

import com.hospitalsaude.scheduling.util.StatusAppointment;
import com.hospitalsaude.scheduling.util.StatusAppointmentConverter;
import com.hospitalsaude.scheduling.util.TypeAppointment;
import com.hospitalsaude.scheduling.util.TypeAppointmentConverter;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "tbl_consulta")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_consulta")
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_medico")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private Patient patient;

    @Column(name = "data_agendamento", nullable = false)
    private LocalDateTime dateAppointment;

    @Column(name = "data_consulta", nullable = false)
    private LocalDate date;

    @Column(name = "hora_consulta", nullable = false)
    private LocalTime time;

    @Convert(converter = StatusAppointmentConverter.class)
    @Column(name = "status_consulta", nullable = false)
    private StatusAppointment status;

    @Convert(converter = TypeAppointmentConverter.class)
    @Column(name = "tipo_consulta", nullable = false)
    private TypeAppointment type;

    @Column(name = "observacao")
    private String note;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public StatusAppointment getStatus() {
        return status;
    }

    public void setStatus(StatusAppointment status) {
        this.status = status;
    }

    public TypeAppointment getType() {
        return type;
    }

    public void setType(TypeAppointment type) {
        this.type = type;
    }

    public LocalDateTime getDateAppointment() {
        return dateAppointment;
    }

    public void setDateAppointment(LocalDateTime dateAppointment) {
        this.dateAppointment = dateAppointment;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
