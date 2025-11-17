package com.hospitalsaude.scheduling.repository;

import com.hospitalsaude.scheduling.model.Doctor;
import com.hospitalsaude.scheduling.util.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

    public Doctor findByCrm(int crm);
    public ArrayList<Doctor> findBySpecialty(Specialty specialty);
}
