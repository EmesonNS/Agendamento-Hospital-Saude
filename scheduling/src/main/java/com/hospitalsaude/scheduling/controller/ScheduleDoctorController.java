package com.hospitalsaude.scheduling.controller;

import com.hospitalsaude.scheduling.dto.ScheduleDoctorRequestDTO;
import com.hospitalsaude.scheduling.dto.ScheduleDoctorResponseDTO;
import com.hospitalsaude.scheduling.service.interfaces.IScheduleDoctorService;
import com.hospitalsaude.scheduling.util.DayWeek;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule")
public class ScheduleDoctorController {

    private final IScheduleDoctorService scheduleDoctorService;

    public ScheduleDoctorController(IScheduleDoctorService service) {
        this.scheduleDoctorService = service;
    }

    @PostMapping
    public ResponseEntity<ScheduleDoctorResponseDTO> addNew(@RequestBody ScheduleDoctorRequestDTO scheduleDoctorDTO){
        ScheduleDoctorResponseDTO result = scheduleDoctorService.addNewSchedule(scheduleDoctorDTO);
        return result != null ? ResponseEntity.status(201).body(result) : ResponseEntity.badRequest().build();
    }

    @GetMapping
    public ResponseEntity<List<ScheduleDoctorResponseDTO>> findAll(){
        return ResponseEntity.ok(scheduleDoctorService.findAllSchedule());
    }

    @GetMapping(value = "/search", params = "day-week")
    public ResponseEntity<List<ScheduleDoctorResponseDTO>> findByDayWeek(@RequestParam(name = "day-week") String dayWeek){
        List<ScheduleDoctorResponseDTO> result = scheduleDoctorService.findByDayWeek(DayWeek.valueOf(dayWeek.toUpperCase()));
        return !result.isEmpty() ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/search", params = "doctorId")
    public ResponseEntity<List<ScheduleDoctorResponseDTO>> findByDoctor(@RequestParam(name = "doctorId") int doctorId){
        List<ScheduleDoctorResponseDTO> result = scheduleDoctorService.findByDoctor(doctorId);
        return !result.isEmpty() ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<ScheduleDoctorResponseDTO> alterSchedule(
            @PathVariable int id,
            @RequestBody ScheduleDoctorRequestDTO scheduleDoctorDTO){
        ScheduleDoctorResponseDTO result = scheduleDoctorService.modifySchedule(id, scheduleDoctorDTO);
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id){
        if (scheduleDoctorService.findById(id) != null){
            scheduleDoctorService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
