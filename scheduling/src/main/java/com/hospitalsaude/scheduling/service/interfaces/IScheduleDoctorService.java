package com.hospitalsaude.scheduling.service.interfaces;

import com.hospitalsaude.scheduling.model.ScheduleDoctor;
import com.hospitalsaude.scheduling.util.DayWeek;

import java.util.ArrayList;

public interface IScheduleDoctorService {
    public ScheduleDoctor addNewSchedule(ScheduleDoctor scheduleDoctor);
    public ScheduleDoctor modifySchedule(ScheduleDoctor scheduleDoctor);
    public ArrayList<ScheduleDoctor> findAllSchedule();
    public ArrayList<ScheduleDoctor> findByDayWeek(DayWeek dayWeek);
}
