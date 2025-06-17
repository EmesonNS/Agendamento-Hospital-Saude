package com.hospitalsaude.scheduling.controller;

import com.hospitalsaude.scheduling.model.Patient;
import com.hospitalsaude.scheduling.service.interfaces.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private IPatientService service;

    @GetMapping
    public ResponseEntity<ArrayList<Patient>> findAll(){
        return ResponseEntity.ok(service.findAllPatient());
    }

    @GetMapping("/search")
    public ResponseEntity<Patient> findByCpf(@RequestParam(name = "cpf") String cpf){
        Patient result = service.findByCpf(cpf);
        if (result != null){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Patient> addNew(@RequestBody Patient patient){
        Patient result = service.addNewPatient(patient);
        if (result != null){
            return ResponseEntity.status(201).body(result);
        }
        return ResponseEntity.badRequest().build();
    }

    public ResponseEntity<Patient> alterPatient(int id, Patient patient){
        Patient result = service.modifyPatient(patient);
        if (result != null){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().build();
    }
}
