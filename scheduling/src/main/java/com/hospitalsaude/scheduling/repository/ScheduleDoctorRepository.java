package com.hospitalsaude.scheduling.repository;

import com.hospitalsaude.scheduling.model.ScheduleDoctor;
import com.hospitalsaude.scheduling.util.DayWeek;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface ScheduleDoctorRepository extends JpaRepository<ScheduleDoctor, Integer> {
    public ArrayList<ScheduleDoctor> findByDayWeek(DayWeek dayWeek);
}
