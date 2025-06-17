package com.hospitalsaude.scheduling.service.implement;

import com.hospitalsaude.scheduling.model.Doctor;
import com.hospitalsaude.scheduling.repository.DoctorRepository;
import com.hospitalsaude.scheduling.service.interfaces.IDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class DoctorServiceImpl implements IDoctorService {

    @Autowired
    private DoctorRepository repository;

    @Override
    public Doctor addNewDoctor(Doctor doctor) {
        try {
            repository.save(doctor);
            return doctor;
        } catch (IllegalArgumentException e) {
            System.out.println("DEBUG: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Doctor modifyDoctor(Doctor doctor) {
        try {
            repository.save(doctor);
            return doctor;
        } catch (Exception e) {
            System.out.println("DEBUG: " + e.getMessage());
        }
        return null;
    }

    @Override
    public ArrayList<Doctor> findAllDoctor() {
        return (ArrayList<Doctor>) repository.findAll();
    }

    @Override
    public Doctor findByCrm(int crm) {
        return repository.findByCrm(crm);
    }
}
