package com.hospitalsaude.scheduling.controller;

import com.hospitalsaude.scheduling.model.Doctor;
import com.hospitalsaude.scheduling.service.IDoctorService;
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
    public ResponseEntity<ArrayList<Doctor>> recoverAll(){
        return ResponseEntity.ok(service.recoverAllDoctor());
    }

    @GetMapping("/search")
    public ResponseEntity<Doctor> recoverByCrm(@RequestParam(name = "crm") int crm){
        Doctor result = service.recoverByCrm(crm);
        if (result != null){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    private ResponseEntity<Doctor> addNew(@RequestBody Doctor doctor){
        Doctor result = service.addNewDoctor(doctor);
        if (result != null){
            return ResponseEntity.status(201).body(result);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    private ResponseEntity<Doctor> alterDoctor(@PathVariable int id ,@RequestBody Doctor doctor){
        Doctor result = service.modifyDoctor(doctor);
        if (result != null){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().build();
    }
}
