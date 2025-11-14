package com.hospitalsaude.scheduling.controller;

import com.hospitalsaude.scheduling.dto.AppointmentRequestDTO;
import com.hospitalsaude.scheduling.dto.AppointmentResponseDTO;
import com.hospitalsaude.scheduling.dto.UpdateAppointmentStatusDTO;
import com.hospitalsaude.scheduling.service.interfaces.IAppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/appointment")
@Tag(name = "Agendamentos", description = "Marcação e gerenciamento de consultas")
@SecurityRequirement(name = "Bearer Authentication")
public class AppointmentController {

    private final IAppointmentService appointmentService;

    public AppointmentController(IAppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @Operation(summary = "Agendar nova consulta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Consulta agendada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Médico ou Paciente não encontrados."),
            @ApiResponse(responseCode = "409", description = "Conflito: O horário solicitado já está ocupado.")
    })
    @PostMapping
    public ResponseEntity<AppointmentResponseDTO> addNew(
            @Valid @RequestBody AppointmentRequestDTO appointmentDTO){
        AppointmentResponseDTO result = appointmentService.addNewAppointment(appointmentDTO);
        return ResponseEntity.status(201).body(result);
    }

    @Operation(summary = "Listar todos os agendamentos")
    @GetMapping
    public ResponseEntity<List<AppointmentResponseDTO>> findAll(){
        return ResponseEntity.ok(appointmentService.findAllAppointment());
    }

    @Operation(summary = "Buscar agendamento por ID")
    @ApiResponse(responseCode = "404", description = "Agendamento não encontrado.")
    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponseDTO> findById(@PathVariable int id){
        AppointmentResponseDTO result = appointmentService.findById(id);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Buscar agendamentos por Data")
    @GetMapping(value = "/search", params = "date")
    public ResponseEntity<List<AppointmentResponseDTO>> findByDate(@RequestParam(name = "date") LocalDate date){
        List<AppointmentResponseDTO> result = appointmentService.findByDate(date);
        return !result.isEmpty() ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Buscar agendamentos por Médico")
    @GetMapping(value = "/search", params = "doctorId")
    public ResponseEntity<List<AppointmentResponseDTO>> findByDoctor(@RequestParam(name = "doctorId") int doctorId){
        List<AppointmentResponseDTO> result = appointmentService.findByDoctor(doctorId);
        return !result.isEmpty() ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Buscar agendamentos por Paciente")
    @GetMapping(value = "/search", params = "patientId")
    public ResponseEntity<List<AppointmentResponseDTO>> findByPatient(@RequestParam(name = "patientId") int patientId){
        List<AppointmentResponseDTO> result = appointmentService.findByPatient(patientId);
        return !result.isEmpty() ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Buscar por Médico e Data")
    @GetMapping(value = "/search", params = {"doctorId", "date"})
    public ResponseEntity<List<AppointmentResponseDTO>> findByDoctorAndDate(
            @RequestParam(name = "doctorId") int doctorId,
            @RequestParam(name = "date") LocalDate date){
        List<AppointmentResponseDTO> result = appointmentService.findByDoctorAndDate(doctorId, date);
        return !result.isEmpty() ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Atualizar status da consulta", description = "Permite alterar o status (ex: REALIZADA, CANCELADA) e adicionar observações.")
    @ApiResponse(responseCode = "404", description = "Agendamento não encontrado.")
    @PatchMapping("/{id}/status")
    public ResponseEntity<AppointmentResponseDTO> alterAppointmentStatus(
            @PathVariable int id,
            @Valid @RequestBody UpdateAppointmentStatusDTO statusDTO){
        AppointmentResponseDTO result = appointmentService.updateAppointmentStatus(id, statusDTO.status(), statusDTO.note());
        return ResponseEntity.ok(result);
    }

}
