package com.hospitalsaude.scheduling.service.interfaces;

import com.hospitalsaude.scheduling.model.Patient;

import java.util.ArrayList;

public interface IPatientService {
    public Patient addNewPatient(Patient patient);
    public Patient modifyPatient(Patient patient);
    public ArrayList<Patient> findAllPatient();
    public Patient findByCpf(String cpf);
}
