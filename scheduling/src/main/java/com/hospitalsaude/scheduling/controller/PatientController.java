package com.hospitalsaude.scheduling.controller;

import com.hospitalsaude.scheduling.dto.PatientRequestDTO;
import com.hospitalsaude.scheduling.dto.PatientResponseDTO;
import com.hospitalsaude.scheduling.service.interfaces.IPatientService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {

    private final IPatientService service;

    public PatientController(IPatientService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PatientResponseDTO> addNew(
            @Valid @RequestBody PatientRequestDTO patientDTO){
        PatientResponseDTO result = service.addNewPatient(patientDTO);
        return result != null ? ResponseEntity.status(201).body(result) : ResponseEntity.badRequest().build();
    }

    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> findAll(){
        return ResponseEntity.ok(service.findAllPatient());
    }

    @GetMapping(value = "/search", params = "id")
    public ResponseEntity<PatientResponseDTO> findById(@RequestParam(name = "id") int id){
        PatientResponseDTO result = service.findById(id);
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/search", params = "cpf")
    public ResponseEntity<PatientResponseDTO> findByCpf(@RequestParam(name = "cpf") String cpf){
        PatientResponseDTO result = service.findByCpf(cpf);
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/search", params = "email")
    public ResponseEntity<PatientResponseDTO> findByEmail(@RequestParam(name = "email") String email){
        PatientResponseDTO result = service.findByEmail(email);
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> alterPatient(
            @PathVariable int id,
            @Valid @RequestBody PatientRequestDTO patientDTO){
        PatientResponseDTO result = service.modifyPatient(id, patientDTO);
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
}
