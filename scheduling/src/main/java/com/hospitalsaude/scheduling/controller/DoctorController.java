package com.hospitalsaude.scheduling.controller;

import com.hospitalsaude.scheduling.dto.DoctorRequestDTO;
import com.hospitalsaude.scheduling.dto.DoctorResponseDTO;
import com.hospitalsaude.scheduling.service.interfaces.IDoctorService;
import com.hospitalsaude.scheduling.util.DayWeek;
import com.hospitalsaude.scheduling.util.Specialty;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/doctor")
@Tag(name = "Médicos", description = "Gerenciamento de médicos e consulta de horários")
public class DoctorController {

    private final IDoctorService service;

    public DoctorController(IDoctorService service) {
        this.service = service;
    }

    @Operation(summary = "Cadastrar novo médico", description = "Requer ROLE_ADMIN.")
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping
    public ResponseEntity<DoctorResponseDTO> addNew(
            @Valid @RequestBody DoctorRequestDTO doctorDTO){
        DoctorResponseDTO result = service.addNewDoctor(doctorDTO);
        return ResponseEntity.status(201).body(result);
    }

    @Operation(summary = "Listar todos os médicos", description = "Requer ROLE_ADMIN.")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping
    public ResponseEntity<List<DoctorResponseDTO>> findAll(){
        return ResponseEntity.ok(service.findAllDoctor());
    }

    @Operation(summary = "Buscar médico por ID")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping(value = "/search", params = "id")
    public ResponseEntity<DoctorResponseDTO> findById(@RequestParam(name = "id") int id){
        DoctorResponseDTO result = service.findById(id);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Buscar médico por CRM")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping(value = "/search", params = "crm")
    public ResponseEntity<DoctorResponseDTO> findByCrm(@RequestParam(name = "crm") int crm){
        DoctorResponseDTO result = service.findByCrm(crm);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Buscar médico por especialidade")
    @SecurityRequirement(name = "Bearer Authentication")
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

    @Operation(summary = "Atualizar médico", description = "Requer ROLE_ADMIN.")
    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping("/{id}")
    public ResponseEntity<DoctorResponseDTO> alterDoctor(
            @PathVariable int id,
            @Valid @RequestBody DoctorRequestDTO doctorDTO){
        DoctorResponseDTO result = service.modifyDoctor(id, doctorDTO);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Excluir médico", description = "Requer ROLE_ADMIN.")
    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id){
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Listar horários de atendimento", description = "Retorna a configuração semanal de horários do médico. Público.")
    @GetMapping("/{id}/opening-times")
    public ResponseEntity<Map<DayWeek, List<String>>> findOpeningTimes(@PathVariable(name = "id") int doctorId){
        Map<DayWeek, List<String>> result = service.openingTimes(doctorId);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Buscar horários disponíveis na data", description = "Calcula os horários livres para agendamento em uma data específica. Público.")
    @GetMapping("/{id}/available-times")
    public ResponseEntity<List<String>> findAvailableTimesByDate(
            @PathVariable(name = "id") int doctorId,
            @RequestParam(name = "date") LocalDate date){
        List<String> result = service.findAvailableTimesByDate(doctorId, date);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Listar especialidades disponíveis", description = "Público.")
    @GetMapping("/specialty")
    public ResponseEntity<Specialty[]> findAllSpecialty(){
        return ResponseEntity.ok(service.findAllSpeciality());
    }
}
