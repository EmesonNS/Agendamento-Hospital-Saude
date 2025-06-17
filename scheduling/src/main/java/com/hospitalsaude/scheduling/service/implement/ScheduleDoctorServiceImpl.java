package com.hospitalsaude.scheduling.service.implement;

import com.hospitalsaude.scheduling.model.ScheduleDoctor;
import com.hospitalsaude.scheduling.repository.ScheduleDoctorRepository;
import com.hospitalsaude.scheduling.service.interfaces.IScheduleDoctorService;
import com.hospitalsaude.scheduling.util.DayWeek;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

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
    public ArrayList<ScheduleDoctor> findAllSchedule() {
        return (ArrayList<ScheduleDoctor>) repository.findAll();
    }

    @Override
    public ArrayList<ScheduleDoctor> findByDayWeek(DayWeek dayWeek) {
        return repository.findByDayWeek(dayWeek);
    }
}
