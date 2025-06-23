package com.hospitalsaude.scheduling.repository;

import com.hospitalsaude.scheduling.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
}
