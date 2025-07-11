package com.hospitalsaude.scheduling.service.interfaces;

import com.hospitalsaude.scheduling.model.Patient;

import java.util.ArrayList;

public interface IPatientService {
    public Patient addNewPatient(Patient patient);
    public Patient modifyPatient(Patient patient);
    public ArrayList<Patient> findAllPatient();
    public Patient findById(int id);
    public Patient findByCpf(String cpf);
    public Patient findByEmail(String email);
    public void deleteById(int id);
}
