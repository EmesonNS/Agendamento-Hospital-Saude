package com.hospitalsaude.scheduling.repository;

import com.hospitalsaude.scheduling.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
    public Patient findByCpf(String cpf);
}
