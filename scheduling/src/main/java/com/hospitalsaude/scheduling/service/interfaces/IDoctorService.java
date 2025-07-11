package com.hospitalsaude.scheduling.service.interfaces;

import com.hospitalsaude.scheduling.model.Doctor;
import com.hospitalsaude.scheduling.util.Specialty;

import java.util.ArrayList;

public interface IDoctorService {

    public Doctor addNewDoctor(Doctor doctor);
    public Doctor modifyDoctor(Doctor doctor);
    public ArrayList<Doctor> findAllDoctor();
    public Doctor findById(int id);
    public Doctor findByCrm(int crm);
    public ArrayList<Doctor> findBySpecialty(Specialty specialty);
    public void deleteById(int id);

    public String findAvailableTimes(Doctor doctor);

    public Specialty[] findAllSpeciality();
}
