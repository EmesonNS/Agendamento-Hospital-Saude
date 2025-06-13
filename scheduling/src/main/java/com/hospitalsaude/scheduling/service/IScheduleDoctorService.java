package com.hospitalsaude.scheduling.service;

import com.hospitalsaude.scheduling.model.ScheduleDoctor;
import com.hospitalsaude.scheduling.util.DayWeek;

import java.util.ArrayList;

public interface IScheduleDoctorService {
    public ScheduleDoctor addNewSchedule(ScheduleDoctor scheduleDoctor);
    public ScheduleDoctor modifySchedule(ScheduleDoctor scheduleDoctor);
    public ArrayList<ScheduleDoctor> recoverAllSchedule();
    public ArrayList<ScheduleDoctor> recoverByDayWeek(DayWeek dayWeek);
}
