package com.hospitalsaude.scheduling.controller;

import com.hospitalsaude.scheduling.model.Appointment;
import com.hospitalsaude.scheduling.service.interfaces.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private IAppointmentService service;

    @GetMapping
    public ResponseEntity<ArrayList<Appointment>> findAll(){
        return ResponseEntity.ok(service.findAllAppointment());
    }

    @PostMapping
    public ResponseEntity<Appointment> addNew(@RequestBody Appointment appointment){
        Appointment result = service.addNewAppointment(appointment);
        if (result != null){
            return ResponseEntity.status(201).body(result);
        }
        return ResponseEntity.badRequest().build();
    }
}
