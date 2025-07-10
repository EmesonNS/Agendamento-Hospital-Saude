package com.hospitalsaude.scheduling.controller;

import com.hospitalsaude.scheduling.model.Doctor;
import com.hospitalsaude.scheduling.service.interfaces.IDoctorService;
import com.hospitalsaude.scheduling.util.Specialty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private IDoctorService service;

    @GetMapping
    public ResponseEntity<ArrayList<Doctor>> findAll(){
        return ResponseEntity.ok(service.findAllDoctor());
    }

    @GetMapping(value = "/search", params = "id")
    public ResponseEntity<Doctor> findById(@RequestParam int id){
        Doctor result = service.findById(id);
        if (result != null){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/search", params = "crm")
    public ResponseEntity<Doctor> findByCrm(@RequestParam int crm){
        Doctor result = service.findByCrm(crm);
        if (result != null){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/search", params = "specialty")
    public ResponseEntity<?> findBySpecialty(@RequestParam String specialty){
        try {
            Specialty enumValue = Specialty.valueOf(specialty.toUpperCase());
            ArrayList<Doctor> result = service.findBySpecialty(enumValue);
            if (!result.isEmpty()){
                return ResponseEntity.ok(result);
            }
            return ResponseEntity.notFound().build();
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body("Especialidade inválida: " + specialty);
        }

    }

    @PostMapping
    public ResponseEntity<Doctor> addNew(@RequestBody Doctor doctor){
        Doctor result = service.addNewDoctor(doctor);
        if (result != null){
            return ResponseEntity.status(201).body(result);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Doctor> alterDoctor(@PathVariable int id ,@RequestBody Doctor doctor){
        doctor.setId(id);
        Doctor result = service.modifyDoctor(doctor);
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

    @GetMapping("/{id}/available-times")
    public ResponseEntity<String> findAvailableTimes(@PathVariable(name = "id") Doctor doctor){
        return ResponseEntity.ok(service.findAvailableTimes(doctor));
    }

    @GetMapping("/specialty")
    public ResponseEntity<Specialty[]> findAllSpecialty(){
        return ResponseEntity.ok(service.findAllSpeciality());
    }
}
