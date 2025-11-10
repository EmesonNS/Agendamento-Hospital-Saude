package com.hospitalsaude.scheduling.controller;

import com.hospitalsaude.scheduling.dto.AppointmentRequestDTO;
import com.hospitalsaude.scheduling.dto.AppointmentResponseDTO;
import com.hospitalsaude.scheduling.dto.UpdateAppointmentStatusDTO;
import com.hospitalsaude.scheduling.service.interfaces.IAppointmentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    private final IAppointmentService appointmentService;

    public AppointmentController(IAppointmentService service) {
        this.appointmentService = service;
    }

    @PostMapping
    public ResponseEntity<AppointmentResponseDTO> addNew(
            @Valid @RequestBody AppointmentRequestDTO appointmentDTO){
        AppointmentResponseDTO result = appointmentService.addNewAppointment(appointmentDTO);
        return result != null ? ResponseEntity.status(201).body(result) : ResponseEntity.badRequest().build();
    }

    @GetMapping
    public ResponseEntity<List<AppointmentResponseDTO>> findAll(){
        return ResponseEntity.ok(appointmentService.findAllAppointment());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponseDTO> findById(@PathVariable int id){
        AppointmentResponseDTO result = appointmentService.findById(id);
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/search", params = "date")
    public ResponseEntity<List<AppointmentResponseDTO>> findByDate(@RequestParam(name = "date") LocalDate date){
        List<AppointmentResponseDTO> result = appointmentService.findByDate(date);
        return !result.isEmpty() ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/search", params = "doctorId")
    public ResponseEntity<List<AppointmentResponseDTO>> findByDoctor(@RequestParam(name = "doctorId") int doctorId){
        List<AppointmentResponseDTO> result = appointmentService.findByDoctor(doctorId);
        return !result.isEmpty() ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/search", params = "patientId")
    public ResponseEntity<List<AppointmentResponseDTO>> findByPatient(@RequestParam(name = "patientId") int patientId){
        List<AppointmentResponseDTO> result = appointmentService.findByPatient(patientId);
        return !result.isEmpty() ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/search", params = {"doctorId", "date"})
    public ResponseEntity<List<AppointmentResponseDTO>> findByDoctorAndDate(
            @RequestParam(name = "doctorId") int doctorId,
            @RequestParam(name = "date") LocalDate date){
        List<AppointmentResponseDTO> result = appointmentService.findByDoctorAndDate(doctorId, date);
        return !result.isEmpty() ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<AppointmentResponseDTO> alterAppointmentStatus(
            @PathVariable int id,
            @Valid @RequestBody UpdateAppointmentStatusDTO statusDTO){
        AppointmentResponseDTO result = appointmentService.updateAppointmentStatus(id, statusDTO.status(), statusDTO.note());
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.badRequest().build();
    }

}
