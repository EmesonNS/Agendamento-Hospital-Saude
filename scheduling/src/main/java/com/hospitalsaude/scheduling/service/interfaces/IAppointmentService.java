package com.hospitalsaude.scheduling.service.interfaces;

import com.hospitalsaude.scheduling.dto.AppointmentRequestDTO;
import com.hospitalsaude.scheduling.dto.AppointmentResponseDTO;
import com.hospitalsaude.scheduling.util.StatusAppointment;

import java.time.LocalDate;
import java.util.List;

public interface IAppointmentService {

    public AppointmentResponseDTO addNewAppointment(AppointmentRequestDTO appointmentDTO);
    public List<AppointmentResponseDTO> findAllAppointment();
    public AppointmentResponseDTO findById(int id);
    public List<AppointmentResponseDTO> findByDate(LocalDate date);
    public List<AppointmentResponseDTO> findByDoctor(int doctorId);
    public List<AppointmentResponseDTO> findByPatient(int patientId);
    public List<AppointmentResponseDTO> findByDoctorAndDate(int doctorId, LocalDate date);
    public AppointmentResponseDTO updateAppointmentStatus(int id, StatusAppointment status, String note);
}
