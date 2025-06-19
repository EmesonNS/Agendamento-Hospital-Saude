package com.hospitalsaude.scheduling.controller;

import com.hospitalsaude.scheduling.model.ScheduleDoctor;
import com.hospitalsaude.scheduling.service.interfaces.IScheduleDoctorService;
import com.hospitalsaude.scheduling.util.DayWeek;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/schedule")
public class ScheduleDoctorController {

    @Autowired
    private IScheduleDoctorService service;

    @GetMapping
    public ResponseEntity<ArrayList<ScheduleDoctor>> findAll(){
        return ResponseEntity.ok(service.findAllSchedule());
    }

    @GetMapping("/search")
    public ResponseEntity<ArrayList<ScheduleDoctor>> findByDayWeek(@RequestParam(name = "dayWeek") DayWeek dayWeek){
        ArrayList<ScheduleDoctor> result = service.findByDayWeek(dayWeek);
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
        ScheduleDoctor result = service.modifySchedule(scheduleDoctor);
        if (result != null){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().build();
    }

}
