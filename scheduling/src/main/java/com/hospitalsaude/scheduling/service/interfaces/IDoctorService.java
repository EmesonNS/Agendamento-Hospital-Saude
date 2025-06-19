package com.hospitalsaude.scheduling.service.interfaces;

import com.hospitalsaude.scheduling.model.Doctor;

import java.util.ArrayList;

public interface IDoctorService {

    public Doctor addNewDoctor(Doctor doctor);
    public Doctor modifyDoctor(Doctor doctor);
    public ArrayList<Doctor> findAllDoctor();
    public Doctor findByCrm(int crm);
}
