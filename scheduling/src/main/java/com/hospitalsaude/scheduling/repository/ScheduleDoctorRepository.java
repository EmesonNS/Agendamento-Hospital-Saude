package com.hospitalsaude.scheduling.repository;

import com.hospitalsaude.scheduling.model.Doctor;
import com.hospitalsaude.scheduling.model.ScheduleDoctor;
import com.hospitalsaude.scheduling.util.DayWeek;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleDoctorRepository extends JpaRepository<ScheduleDoctor, Integer> {
    //@Query("SELECT s FROM ScheduleDoctor s WHERE s.dayWeek IN :dayWeek")
    public List<ScheduleDoctor> findByDayWeekContaining(DayWeek dayWeek);

    public List<ScheduleDoctor> findByDoctor(Doctor doctor);
}
