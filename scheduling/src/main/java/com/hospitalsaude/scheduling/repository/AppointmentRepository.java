package com.hospitalsaude.scheduling.repository;

import com.hospitalsaude.scheduling.model.Appointment;
import com.hospitalsaude.scheduling.model.Doctor;
import com.hospitalsaude.scheduling.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    public List<Appointment> findByDate(LocalDate date);
    public List<Appointment> findByDoctor(Doctor doctor);
    public List<Appointment> findByPatient(Patient patient);
    public List<Appointment> findByDoctorAndDate(Doctor doctor, LocalDate date);
}
