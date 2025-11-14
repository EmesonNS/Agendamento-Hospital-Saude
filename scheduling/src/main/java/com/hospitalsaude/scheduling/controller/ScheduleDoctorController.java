package com.hospitalsaude.scheduling.controller;

import com.hospitalsaude.scheduling.dto.ScheduleDoctorRequestDTO;
import com.hospitalsaude.scheduling.dto.ScheduleDoctorResponseDTO;
import com.hospitalsaude.scheduling.service.interfaces.IScheduleDoctorService;
import com.hospitalsaude.scheduling.util.DayWeek;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule")
@Tag(name = "Agenda Médicas", description = "Configuração de dias e horários de trabalho dos médicos")
@SecurityRequirement(name = "Bearer Authentication")
public class ScheduleDoctorController {

    private final IScheduleDoctorService scheduleDoctorService;

    public ScheduleDoctorController(IScheduleDoctorService service) {
        this.scheduleDoctorService = service;
    }

    @Operation(summary = "Criar nova agenda", description = "Define os dias e horários de trabalho de um médico. Requer ROLE_ADMIN.")
    @ApiResponse(responseCode = "201", description = "Agenda criado com sucesso.")
    @PostMapping
    public ResponseEntity<ScheduleDoctorResponseDTO> addNew(
            @Valid @RequestBody ScheduleDoctorRequestDTO scheduleDoctorDTO){
        ScheduleDoctorResponseDTO result = scheduleDoctorService.addNewSchedule(scheduleDoctorDTO);
        return ResponseEntity.status(201).body(result);
    }

    @Operation(summary = "Listar todas as agendas")
    @GetMapping
    public ResponseEntity<List<ScheduleDoctorResponseDTO>> findAll(){
        return ResponseEntity.ok(scheduleDoctorService.findAllSchedule());
    }

    @Operation(summary = "Buscar agendas por Dia da Semana")
    @GetMapping(value = "/search", params = "day-week")
    public ResponseEntity<List<ScheduleDoctorResponseDTO>> findByDayWeek(@RequestParam(name = "day-week") String dayWeek){
        List<ScheduleDoctorResponseDTO> result = scheduleDoctorService.findByDayWeek(DayWeek.valueOf(dayWeek.toUpperCase()));
        return !result.isEmpty() ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Buscar agenda de um Médico")
    @GetMapping(value = "/search", params = "doctorId")
    public ResponseEntity<List<ScheduleDoctorResponseDTO>> findByDoctor(@RequestParam(name = "doctorId") int doctorId){
        List<ScheduleDoctorResponseDTO> result = scheduleDoctorService.findByDoctor(doctorId);
        return !result.isEmpty() ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Atualizar agenda", description = "Requer ROLE_ADMIN.")
    @PutMapping("{id}")
    public ResponseEntity<ScheduleDoctorResponseDTO> alterSchedule(
            @PathVariable int id,
            @Valid @RequestBody ScheduleDoctorRequestDTO scheduleDoctorDTO){
        ScheduleDoctorResponseDTO result = scheduleDoctorService.modifySchedule(id, scheduleDoctorDTO);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Excluir agenda", description = "Requer ROLE_ADMIN.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id){
        scheduleDoctorService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
