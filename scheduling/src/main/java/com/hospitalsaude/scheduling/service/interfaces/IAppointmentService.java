package com.hospitalsaude.scheduling.service.interfaces;

import com.hospitalsaude.scheduling.model.Appointment;
import com.hospitalsaude.scheduling.model.Doctor;
import com.hospitalsaude.scheduling.model.Patient;

import java.time.LocalDate;
import java.util.List;

public interface IAppointmentService {

    public Appointment addNewAppointment(Appointment appointment);
    public List<Appointment> findAllAppointment();
    public Appointment findById(int id);
    public List<Appointment> findByDate(LocalDate date);
    public List<Appointment> findByDoctor(Doctor doctor);
    public List<Appointment> findByPatient(Patient patient);
    public List<Appointment> findByDoctorAndDate(Doctor doctor, LocalDate date);
    public Appointment modifyAppointment(Appointment appointment);
}
