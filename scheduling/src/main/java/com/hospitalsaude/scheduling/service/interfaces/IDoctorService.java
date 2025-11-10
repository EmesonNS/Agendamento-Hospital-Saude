package com.hospitalsaude.scheduling.service.interfaces;

import com.hospitalsaude.scheduling.dto.DoctorRequestDTO;
import com.hospitalsaude.scheduling.dto.DoctorResponseDTO;
import com.hospitalsaude.scheduling.util.DayWeek;
import com.hospitalsaude.scheduling.util.Specialty;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface IDoctorService {

    public DoctorResponseDTO addNewDoctor(DoctorRequestDTO doctorDTO);
    public DoctorResponseDTO modifyDoctor(int id, DoctorRequestDTO doctorDTO);
    public List<DoctorResponseDTO> findAllDoctor();
    public DoctorResponseDTO findById(int id);
    public DoctorResponseDTO findByCrm(int crm);
    public List<DoctorResponseDTO> findBySpecialty(Specialty specialty);
    public void deleteById(int id);

    public Map<DayWeek, List<String>> openingTimes(int doctorId);
    public List<String> findAvailableTimesByDate(int doctorId, LocalDate date);

    public Specialty[] findAllSpeciality();
}
