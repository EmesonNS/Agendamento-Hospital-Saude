package com.hospitalsaude.scheduling.service.implement;

import com.hospitalsaude.scheduling.model.Patient;
import com.hospitalsaude.scheduling.repository.PatientRepository;
import com.hospitalsaude.scheduling.service.interfaces.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class PatientServiceImpl implements IPatientService {

    @Autowired
    private PatientRepository repository;

    @Override
    public Patient addNewPatient(Patient patient) {
        try {
            repository.save(patient);
            return patient;
        } catch (IllegalArgumentException e) {
            System.out.println("DEBUG: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Patient modifyPatient(Patient patient) {
        try {
            repository.save(patient);
            return patient;
        }catch (IllegalArgumentException e){
            System.out.println("DEBUG: " + e.getMessage());
        }
        return null;
    }

    @Override
    public ArrayList<Patient> findAllPatient() {
        return (ArrayList<Patient>) repository.findAll();
    }

    @Override
    public Patient findByCpf(String cpf) {
        return repository.findByCpf(cpf);
    }
}
