package com.hospitalsaude.scheduling.service.implement;

import com.hospitalsaude.scheduling.model.Appointment;
import com.hospitalsaude.scheduling.repository.AppointmentRepository;
import com.hospitalsaude.scheduling.service.interfaces.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class AppointmentServiceImpl implements IAppointmentService {

    @Autowired
    private AppointmentRepository repository;

    @Override
    public Appointment addNewAppointment(Appointment appointment) {
        try {
            repository.save(appointment);
            return appointment;
        }catch (IllegalArgumentException e){
            System.out.println("DEBUG: " + e.getMessage());
        }
        return null;
    }

    @Override
    public ArrayList<Appointment> findAllAppointment() {
        return (ArrayList<Appointment>) repository.findAll();
    }
}
