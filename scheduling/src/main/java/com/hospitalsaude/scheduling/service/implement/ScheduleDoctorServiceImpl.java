package com.hospitalsaude.scheduling.service.implement;

import com.hospitalsaude.scheduling.model.Doctor;
import com.hospitalsaude.scheduling.model.ScheduleDoctor;
import com.hospitalsaude.scheduling.repository.ScheduleDoctorRepository;
import com.hospitalsaude.scheduling.service.interfaces.IScheduleDoctorService;
import com.hospitalsaude.scheduling.util.DayWeek;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ScheduleDoctorServiceImpl implements IScheduleDoctorService {

    @Autowired
    private ScheduleDoctorRepository repository;

    @Override
    public ScheduleDoctor addNewSchedule(ScheduleDoctor scheduleDoctor) {
        try {
            repository.save(scheduleDoctor);
            return scheduleDoctor;
        } catch (IllegalArgumentException e) {
            System.out.println("DEBUG: " + e.getMessage());
        }
        return null;
    }

    @Override
    public ScheduleDoctor modifySchedule(ScheduleDoctor scheduleDoctor) {
        try {
            repository.save(scheduleDoctor);
            return scheduleDoctor;
        } catch (Exception e) {
            System.out.println("DEBUG: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<ScheduleDoctor> findAllSchedule() {
        return (ArrayList<ScheduleDoctor>) repository.findAll();
    }

    @Override
    public ScheduleDoctor findById(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<ScheduleDoctor> findByDayWeek(DayWeek dayWeek) {
        return repository.findByDayWeekContaining(dayWeek);
    }

    @Override
    public List<ScheduleDoctor> findByDoctor(Doctor doctor) {
        return repository.findByDoctor(doctor);
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }
}
