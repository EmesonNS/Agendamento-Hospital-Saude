package com.hospitalsaude.scheduling.service.implement;

import com.hospitalsaude.scheduling.model.Appointment;
import com.hospitalsaude.scheduling.model.Doctor;
import com.hospitalsaude.scheduling.model.Patient;
import com.hospitalsaude.scheduling.repository.AppointmentRepository;
import com.hospitalsaude.scheduling.service.interfaces.IAppointmentService;
import com.hospitalsaude.scheduling.service.interfaces.IDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class AppointmentServiceImpl implements IAppointmentService {

    @Autowired
    private AppointmentRepository repository;

    @Autowired
    private IDoctorService doctorService;

    @Override
    public Appointment addNewAppointment(Appointment appointment) {
        Doctor doctor = appointment.getDoctor();
        LocalDate date = appointment.getDate();
        LocalTime time = appointment.getTime();

        List<String> availableTimes = doctorService.findAvailableTimesByDate(doctor, date);

        if (availableTimes.contains(time.toString())){
            return repository.save(appointment);
        }

        return null;
    }

    @Override
    public List<Appointment> findAllAppointment() {
        return (ArrayList<Appointment>) repository.findAll();
    }

    @Override
    public Appointment findById(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Appointment> findByDate(LocalDate date) {
        return repository.findByDate(date);
    }

    @Override
    public List<Appointment> findByDoctor(Doctor doctor) {
        return repository.findByDoctor(doctor);
    }

    @Override
    public List<Appointment> findByPatient(Patient patient) {
        return repository.findByPatient(patient);
    }

    @Override
    public List<Appointment> findByDoctorAndDate(Doctor doctor, LocalDate date) {
        return repository.findByDoctorAndDate(doctor, date);
    }

    @Override
    public Appointment modifyAppointment(Appointment appointment) {
        try {
            repository.save(appointment);
            return appointment;
        } catch (Exception e) {
            System.out.println("DEBUG: " + e.getMessage());
        }
        return null;
    }
}
