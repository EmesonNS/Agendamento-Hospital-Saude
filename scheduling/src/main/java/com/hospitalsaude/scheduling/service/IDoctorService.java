package com.hospitalsaude.scheduling.service;

import com.hospitalsaude.scheduling.model.Doctor;

import java.util.ArrayList;

public interface IDoctorService {

    public Doctor addNewDoctor(Doctor doctor);
    public Doctor modifyDoctor(Doctor doctor);
    public ArrayList<Doctor> recoverAllDoctor();
    public Doctor recoverByCrm(int crm);
}
