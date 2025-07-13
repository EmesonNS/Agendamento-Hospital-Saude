package com.hospitalsaude.scheduling.service.implement;

import com.hospitalsaude.scheduling.model.Appointment;
import com.hospitalsaude.scheduling.model.Doctor;
import com.hospitalsaude.scheduling.model.ScheduleDoctor;
import com.hospitalsaude.scheduling.repository.AppointmentRepository;
import com.hospitalsaude.scheduling.repository.DoctorRepository;
import com.hospitalsaude.scheduling.repository.ScheduleDoctorRepository;
import com.hospitalsaude.scheduling.service.interfaces.IDoctorService;
import com.hospitalsaude.scheduling.util.DayWeek;
import com.hospitalsaude.scheduling.util.Specialty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.Normalizer;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.*;

@Component
public class DoctorServiceImpl implements IDoctorService {

    @Autowired
    private DoctorRepository repository;

    @Autowired
    private ScheduleDoctorRepository scheduleDoctorRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

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
    public Map<DayWeek, List<String>> openingTimes(Doctor doctor) {
        List<ScheduleDoctor> scheduleDoctorList = scheduleDoctorRepository.findByDoctor(doctor);
        Map<DayWeek, List<String>> scheduleMap = new HashMap<>();

        for (ScheduleDoctor scheduleDoctor : scheduleDoctorList){
            List<DayWeek> dayWeekList = scheduleDoctor.getDayWeek();
            LocalTime startTime = scheduleDoctor.getStartTime();
            LocalTime endTime = scheduleDoctor.getEndTime();

            for (DayWeek day : dayWeekList){
                List<String> times = scheduleMap.getOrDefault(day, new ArrayList<>());
                LocalTime current = startTime;
                while (current.isBefore(endTime)) {
                    times.add(current.toString());
                    current = current.plusMinutes(30);
                }
                scheduleMap.put(day, times);
            }
        }

        return scheduleMap;
    }

    @Override
    public List<String> findAvailableTimesByDate(Doctor doctor, LocalDate date) {
        String dayWeek = date.getDayOfWeek()
                .getDisplayName(TextStyle.FULL, Locale.of("pt", "BR"));
        String dayWeekNormalized = Normalizer.normalize(dayWeek, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .replace("-feira", "");
        DayWeek dayWeekEnum = DayWeek.fromString(dayWeekNormalized);

        List<String> appointmentsTimes = appointmentRepository.findByDoctorAndDate(doctor, date)
                .stream()
                .map(appointment -> appointment.getTime().toString())
                .toList();

        Map<DayWeek, List<String>> openingTimes = this.openingTimes(doctor);
        if (openingTimes.containsKey(dayWeekEnum)){
            List<String> times = openingTimes.get(dayWeekEnum);
            times.removeAll(appointmentsTimes);
            return times;
        }
        return List.of();
    }

    @Override
    public Specialty[] findAllSpeciality() {
        return Specialty.values();
    }
}
