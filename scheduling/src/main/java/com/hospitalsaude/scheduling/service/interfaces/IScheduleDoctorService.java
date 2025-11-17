package com.hospitalsaude.scheduling.service.interfaces;

import com.hospitalsaude.scheduling.dto.ScheduleDoctorRequestDTO;
import com.hospitalsaude.scheduling.dto.ScheduleDoctorResponseDTO;
import com.hospitalsaude.scheduling.util.DayWeek;

import java.util.List;

public interface IScheduleDoctorService {
    public ScheduleDoctorResponseDTO addNewSchedule(ScheduleDoctorRequestDTO scheduleDoctorDTO);
    public ScheduleDoctorResponseDTO modifySchedule(int id, ScheduleDoctorRequestDTO scheduleDoctorDTO);
    public List<ScheduleDoctorResponseDTO> findAllSchedule();
    public ScheduleDoctorResponseDTO findById(int id);
    public List<ScheduleDoctorResponseDTO> findByDayWeek(DayWeek dayWeek);
    public List<ScheduleDoctorResponseDTO> findByDoctor(int doctorId);
    public void deleteById(int id);
}
