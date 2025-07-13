package com.hospitalsaude.scheduling.service.interfaces;

import com.hospitalsaude.scheduling.model.Doctor;
import com.hospitalsaude.scheduling.util.DayWeek;
import com.hospitalsaude.scheduling.util.Specialty;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface IDoctorService {

    public Doctor addNewDoctor(Doctor doctor);
    public Doctor modifyDoctor(Doctor doctor);
    public ArrayList<Doctor> findAllDoctor();
    public Doctor findById(int id);
    public Doctor findByCrm(int crm);
    public ArrayList<Doctor> findBySpecialty(Specialty specialty);
    public void deleteById(int id);

    public Map<DayWeek, List<String>> openingTimes(Doctor doctor);
    public List<String> findAvailableTimesByDate(Doctor doctor, LocalDate date);

    public Specialty[] findAllSpeciality();
}
