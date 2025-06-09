package com.hospitalsaude.scheduling.repository;

import com.hospitalsaude.scheduling.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

    public Doctor findByCrm(int crm);
}
