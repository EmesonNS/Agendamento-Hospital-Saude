package com.hospitalsaude.scheduling.controller;

import com.hospitalsaude.scheduling.dto.DoctorRequestDTO;
import com.hospitalsaude.scheduling.dto.DoctorResponseDTO;
import com.hospitalsaude.scheduling.service.interfaces.IDoctorService;
import com.hospitalsaude.scheduling.util.DayWeek;
import com.hospitalsaude.scheduling.util.Specialty;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    private final IDoctorService service;

    public DoctorController(IDoctorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DoctorResponseDTO> addNew(
            @Valid @RequestBody DoctorRequestDTO doctorDTO){
        DoctorResponseDTO result = service.addNewDoctor(doctorDTO);
        return result != null ? ResponseEntity.status(201).body(result) : ResponseEntity.badRequest().build();
    }

    @GetMapping
    public ResponseEntity<List<DoctorResponseDTO>> findAll(){
        return ResponseEntity.ok(service.findAllDoctor());
    }

    @GetMapping(value = "/search", params = "id")
    public ResponseEntity<DoctorResponseDTO> findById(@RequestParam(name = "id") int id){
        DoctorResponseDTO result = service.findById(id);
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/search", params = "crm")
    public ResponseEntity<DoctorResponseDTO> findByCrm(@RequestParam(name = "crm") int crm){
        DoctorResponseDTO result = service.findByCrm(crm);
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/search", params = "specialty")
    public ResponseEntity<?> findBySpecialty(@RequestParam(name = "specialty") String specialty){
        try {
            Specialty enumValue = Specialty.valueOf(specialty.toUpperCase());
            List<DoctorResponseDTO> result = service.findBySpecialty(enumValue);
            return !result.isEmpty() ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body("Especialidade inválida: " + specialty);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorResponseDTO> alterDoctor(
            @PathVariable int id,
            @Valid @RequestBody DoctorRequestDTO doctorDTO){
        DoctorResponseDTO result = service.modifyDoctor(id, doctorDTO);
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
    public ResponseEntity<Map<DayWeek, List<String>>> findOpeningTimes(@PathVariable(name = "id") int doctorId){
        Map<DayWeek, List<String>> result = service.openingTimes(doctorId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}/available-times")
    public ResponseEntity<List<String>> findAvailableTimesByDate(
            @PathVariable(name = "id") int doctorId,
            @RequestParam(name = "date") LocalDate date){
        List<String> result = service.findAvailableTimesByDate(doctorId, date);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/specialty")
    public ResponseEntity<Specialty[]> findAllSpecialty(){
        return ResponseEntity.ok(service.findAllSpeciality());
    }
}
