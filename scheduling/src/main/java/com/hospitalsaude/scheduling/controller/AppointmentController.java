package com.hospitalsaude.scheduling.controller;

import com.hospitalsaude.scheduling.model.Appointment;
import com.hospitalsaude.scheduling.model.Doctor;
import com.hospitalsaude.scheduling.model.Patient;
import com.hospitalsaude.scheduling.service.interfaces.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private IAppointmentService service;

    @PostMapping
    public ResponseEntity<Appointment> addNew(@RequestBody Appointment appointment){
        appointment.setDateAppointment(LocalDateTime.now());
        Appointment result = service.addNewAppointment(appointment);
        return result != null ? ResponseEntity.status(201).body(result) : ResponseEntity.badRequest().build();
    }

    @GetMapping
    public ResponseEntity<List<Appointment>> findAll(){
        return ResponseEntity.ok(service.findAllAppointment());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> findById(@PathVariable int id){
        Appointment result = service.findById(id);
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/search", params = "date")
    public ResponseEntity<List<Appointment>> findByDate(@RequestParam(name = "date") LocalDate date){
        List<Appointment> result = service.findByDate(date);
        return !result.isEmpty() ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/search", params = "doctor")
    public ResponseEntity<List<Appointment>> findByDoctor(@RequestParam(name = "doctor") Doctor doctor){
        List<Appointment> result = service.findByDoctor(doctor);
        return !result.isEmpty() ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/search", params = "patient")
    public ResponseEntity<List<Appointment>> findByPatient(@RequestParam(name = "patient") Patient patient){
        List<Appointment> result = service.findByPatient(patient);
        return !result.isEmpty() ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/search", params = {"doctor", "date"})
    public ResponseEntity<List<Appointment>> findByDoctorAndDate(@RequestParam(name = "doctor") Doctor doctor,
                                                                 @RequestParam(name = "date") LocalDate date){
        List<Appointment> result = service.findByDoctorAndDate(doctor, date);
        return !result.isEmpty() ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Appointment> alterAppointment(@PathVariable int id, @RequestBody Appointment appointment){
        appointment.setId(id);
        appointment.setDateAppointment(service.findById(id).getDateAppointment());
        Appointment result = service.modifyAppointment(appointment);
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.badRequest().build();
    }

}
