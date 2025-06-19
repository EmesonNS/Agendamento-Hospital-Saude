package com.hospitalsaude.scheduling.service.interfaces;

import com.hospitalsaude.scheduling.model.Appointment;

import java.util.ArrayList;

public interface IAppointmentService {

    public Appointment addNewAppointment(Appointment appointment);
    public ArrayList<Appointment> findAllAppointment();
}
