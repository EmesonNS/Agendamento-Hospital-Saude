package com.hospitalsaude.scheduling.service.implement;

import com.hospitalsaude.scheduling.model.Doctor;
import com.hospitalsaude.scheduling.model.ScheduleDoctor;
import com.hospitalsaude.scheduling.repository.DoctorRepository;
import com.hospitalsaude.scheduling.repository.ScheduleDoctorRepository;
import com.hospitalsaude.scheduling.service.interfaces.IDoctorService;
import com.hospitalsaude.scheduling.util.DayWeek;
import com.hospitalsaude.scheduling.util.Specialty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class DoctorServiceImpl implements IDoctorService {

    @Autowired
    private DoctorRepository repository;

    @Autowired
    private ScheduleDoctorRepository scheduleDoctorRepository;

    @Override
    public Doctor addNewDoctor(Doctor doctor) {
        try {
            repository.save(doctor);
            return doctor;
        } catch (IllegalArgumentException e) {
            System.out.println("DEBUG: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Doctor modifyDoctor(Doctor doctor) {
        try {
            repository.save(doctor);
            return doctor;
        } catch (Exception e) {
            System.out.println("DEBUG: " + e.getMessage());
        }
        return null;
    }

    @Override
    public ArrayList<Doctor> findAllDoctor() {
        return (ArrayList<Doctor>) repository.findAll();
    }

    @Override
    public Doctor findById(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Doctor findByCrm(int crm) {
        return repository.findByCrm(crm);
    }

    @Override
    public ArrayList<Doctor> findBySpecialty(Specialty specialty) {
        return repository.findBySpecialty(specialty);
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }

    @Override
    public String findAvailableTimes(Doctor doctor) {
        List<ScheduleDoctor> scheduleDoctorList = scheduleDoctorRepository.findByDoctor(doctor);
        String result = "{";
        for (ScheduleDoctor scheduleDoctor : scheduleDoctorList) {
            List<DayWeek> dayWeekList = scheduleDoctor.getDayWeek();
            LocalTime startTime = scheduleDoctor.getStartTime();
            LocalTime endTime = scheduleDoctor.getEndTime();

            for (DayWeek day : dayWeekList) {
                result += "\"" +     day.name() + "\":{[";
                LocalTime availableTime = startTime;
                while (availableTime.isBefore(endTime)) {
                    result += "\"" + availableTime + "\",";
                    availableTime = availableTime.plusMinutes(30);
                }
                result += "\"" + availableTime + "\"]},";
            }
        }
        result = result.substring(0, result.length()-1);
        result += "}";

        return result;
    }

    @Override
    public Specialty[] findAllSpeciality() {
        return Specialty.values();
    }
}
