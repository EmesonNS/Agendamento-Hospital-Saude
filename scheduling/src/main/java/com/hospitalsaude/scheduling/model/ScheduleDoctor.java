package com.hospitalsaude.scheduling.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hospitalsaude.scheduling.util.DayWeek;
import com.hospitalsaude.scheduling.util.DayWeekConverter;
import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
@Table(name = "tbl_agenda")
public class ScheduleDoctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_agenda")
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_medico")
    private Doctor doctor;

    @Convert(converter = DayWeekConverter.class)
    @Column(name = "dia_da_semana", nullable = false)
    private DayWeek dayWeek;

    @Column(name = "horario_inicio", nullable = false)
    private LocalTime startTime;
    @Column(name = "horario_fim", nullable = false)
    private LocalTime endTime;

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

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public DayWeek getDayWeek() {
        return dayWeek;
    }

    public void setDayWeek(DayWeek dayWeek) {
        this.dayWeek = dayWeek;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }
}
