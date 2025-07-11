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

    @PostMapping
    public ResponseEntity<Patient> addNew(@RequestBody Patient patient){
        Patient result = service.addNewPatient(patient);
        if (result != null){
            return ResponseEntity.status(201).body(result);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping
    public ResponseEntity<ArrayList<Patient>> findAll(){
        return ResponseEntity.ok(service.findAllPatient());
    }

    @GetMapping(value = "/search", params = "id")
    public ResponseEntity<Patient> findById(@RequestParam(name = "id") int id){
        Patient result = service.findById(id);
        if (result != null){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/search", params = "cpf")
    public ResponseEntity<Patient> findByCpf(@RequestParam(name = "cpf") String cpf){
        Patient result = service.findByCpf(cpf);
        if (result != null){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/search", params = "email")
    public ResponseEntity<Patient> findByEmail(@RequestParam(name = "email") String email){
        Patient result = service.findByEmail(email);
        if (result != null){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> alterPatient(@PathVariable int id,@RequestBody Patient patient){
        patient.setId(id);
        Patient result = service.modifyPatient(patient);
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
