package com.hospitalsaude.scheduling.service.interfaces;

import com.hospitalsaude.scheduling.model.Doctor;
import com.hospitalsaude.scheduling.model.ScheduleDoctor;
import com.hospitalsaude.scheduling.util.DayWeek;

import java.util.List;

public interface IScheduleDoctorService {
    public ScheduleDoctor addNewSchedule(ScheduleDoctor scheduleDoctor);
    public ScheduleDoctor modifySchedule(ScheduleDoctor scheduleDoctor);
    public List<ScheduleDoctor> findAllSchedule();
    public ScheduleDoctor findById(int id);
    public List<ScheduleDoctor> findByDayWeek(DayWeek dayWeek);
    public List<ScheduleDoctor> findByDoctor(Doctor doctor);
    public void deleteById(int id);
}
