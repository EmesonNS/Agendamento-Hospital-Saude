package com.hospitalsaude.scheduling.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospitalsaude.scheduling.model.Doctor;
import com.hospitalsaude.scheduling.service.interfaces.IDoctorService;
import com.hospitalsaude.scheduling.util.DayWeek;
import com.hospitalsaude.scheduling.util.Specialty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private IDoctorService service;

    @PostMapping
    public ResponseEntity<Doctor> addNew(@RequestBody Doctor doctor){
        Doctor result = service.addNewDoctor(doctor);
        return result != null ? ResponseEntity.status(201).body(result) : ResponseEntity.badRequest().build();
    }

    @GetMapping
    public ResponseEntity<ArrayList<Doctor>> findAll(){
        return ResponseEntity.ok(service.findAllDoctor());
    }

    @GetMapping(value = "/search", params = "id")
    public ResponseEntity<Doctor> findById(@RequestParam(name = "id") int id){
        Doctor result = service.findById(id);
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/search", params = "crm")
    public ResponseEntity<Doctor> findByCrm(@RequestParam(name = "crm") int crm){
        Doctor result = service.findByCrm(crm);
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/search", params = "specialty")
    public ResponseEntity<?> findBySpecialty(@RequestParam(name = "specialty") String specialty){
        try {
            Specialty enumValue = Specialty.valueOf(specialty.toUpperCase());
            ArrayList<Doctor> result = service.findBySpecialty(enumValue);
            return !result.isEmpty() ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body("Especialidade inválida: " + specialty);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Doctor> alterDoctor(@PathVariable int id ,@RequestBody Doctor doctor){
        doctor.setId(id);
        Doctor result = service.modifyDoctor(doctor);
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id){
        if (service.findById(id) != null){
            service.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/opening-times")
    public ResponseEntity<String> findOpeningTimes(@PathVariable(name = "id") Doctor doctor){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Map<DayWeek, List<String>> result = service.openingTimes(doctor);
            String resultJson = objectMapper.writeValueAsString(result);
            return ResponseEntity.ok(resultJson);
        }catch (JsonProcessingException e){
            throw new RuntimeException("Erro ao converter para JSON", e);
        }
    }

    @GetMapping("/{id}/available-times")
    public ResponseEntity<String> findAvailableTimesByDate(@PathVariable(name = "id") Doctor doctor,
                                                           @RequestParam(name = "date") LocalDate date){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<String> result = service.findAvailableTimesByDate(doctor, date);
            String resultJson = objectMapper.writeValueAsString(result);
            return ResponseEntity.ok(resultJson);
        }catch (JsonProcessingException e){
            throw new RuntimeException("Erro ao converter para JSON", e);
        }
    }

    @GetMapping("/specialty")
    public ResponseEntity<Specialty[]> findAllSpecialty(){
        return ResponseEntity.ok(service.findAllSpeciality());
    }
}
