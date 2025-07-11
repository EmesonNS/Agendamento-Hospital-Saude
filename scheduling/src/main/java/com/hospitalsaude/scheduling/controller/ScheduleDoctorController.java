package com.hospitalsaude.scheduling.controller;

import com.hospitalsaude.scheduling.model.Doctor;
import com.hospitalsaude.scheduling.model.ScheduleDoctor;
import com.hospitalsaude.scheduling.service.interfaces.IScheduleDoctorService;
import com.hospitalsaude.scheduling.util.DayWeek;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule")
public class ScheduleDoctorController {

    @Autowired
    private IScheduleDoctorService service;

    @GetMapping
    public ResponseEntity<List<ScheduleDoctor>> findAll(){
        return ResponseEntity.ok(service.findAllSchedule());
    }

    @GetMapping(value = "/search", params = "day-week")
    public ResponseEntity<List<ScheduleDoctor>> findByDayWeek(@RequestParam(name = "day-week") String dayWeek){
        List<ScheduleDoctor> result = service.findByDayWeek(DayWeek.valueOf(dayWeek));
        if (result != null){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/search", params = "doctor")
    public ResponseEntity<List<ScheduleDoctor>> findByDoctor(@RequestParam Doctor doctor){
        List<ScheduleDoctor> result = service.findByDoctor(doctor);
        if (result != null){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ScheduleDoctor> addNew(@RequestBody ScheduleDoctor scheduleDoctor){
        ScheduleDoctor result = service.addNewSchedule(scheduleDoctor);
        if(result != null){
            return ResponseEntity.status(201).body(result);
        }
        System.out.println("doutor: " + scheduleDoctor.getDoctor() + "\nDia da Semana: " + scheduleDoctor.getDayWeek() + "\nInicio: " + scheduleDoctor.getStartTime() + "\nFim: " + scheduleDoctor.getEndTime());
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<ScheduleDoctor> alterSchedule(@PathVariable int id, @RequestBody ScheduleDoctor scheduleDoctor){
        scheduleDoctor.setId(id);
        ScheduleDoctor result = service.modifySchedule(scheduleDoctor);
        if (result != null){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id){
        if (service.findById(id) != null){
            service.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
