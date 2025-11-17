package com.hospitalsaude.scheduling.service.implement;

import com.hospitalsaude.scheduling.dto.AppointmentRequestDTO;
import com.hospitalsaude.scheduling.dto.AppointmentResponseDTO;
import com.hospitalsaude.scheduling.exception.AppointmentConflictException;
import com.hospitalsaude.scheduling.exception.ResourceNotFoundException;
import com.hospitalsaude.scheduling.mapper.AppointmentMapper;
import com.hospitalsaude.scheduling.model.Appointment;
import com.hospitalsaude.scheduling.model.Doctor;
import com.hospitalsaude.scheduling.model.Patient;
import com.hospitalsaude.scheduling.repository.AppointmentRepository;
import com.hospitalsaude.scheduling.repository.DoctorRepository;
import com.hospitalsaude.scheduling.repository.PatientRepository;
import com.hospitalsaude.scheduling.service.interfaces.IAppointmentService;
import com.hospitalsaude.scheduling.service.interfaces.IDoctorService;
import com.hospitalsaude.scheduling.util.StatusAppointment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AppointmentServiceImpl implements IAppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final IDoctorService doctorService;
    private final AppointmentMapper appointmentMapper;

    private static final Logger logger = LoggerFactory.getLogger(AppointmentServiceImpl.class);

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, DoctorRepository doctorRepository, PatientRepository patientRepository, IDoctorService doctorService, AppointmentMapper appointmentMapper) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.doctorService = doctorService;
        this.appointmentMapper = appointmentMapper;
    }

    @Override
    public AppointmentResponseDTO addNewAppointment(AppointmentRequestDTO appointmentDTO) {
        int doctorId = appointmentDTO.doctorId();
        LocalDate date = appointmentDTO.date();
        LocalTime time = appointmentDTO.time();

        List<String> availableTimes = doctorService.findAvailableTimesByDate(doctorId, date);

        if (availableTimes.contains(time.toString())){
            Appointment newAppointment = appointmentMapper.toEntity(appointmentDTO);

            newAppointment.setStatus(StatusAppointment.AGENDADA);
            newAppointment.setDateAppointment(LocalDateTime.now());

            Appointment savedAppointment = appointmentRepository.save(newAppointment);
            return appointmentMapper.toResponseDTO(savedAppointment);
        }

        logger.warn("Horário {} no dia {} não disponível para o médico ID {}.", time, date, doctorId);
        throw new AppointmentConflictException("O horário solicitado não está disponível para agendamento.",
                                               "Não foi possível agendar a consulta no horário selecionado. Verifique os horários disponíveis e tente novamente.");
    }

    @Override
    public List<AppointmentResponseDTO> findAllAppointment() {
        return appointmentRepository.findAll()
                .stream()
                .map(appointmentMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AppointmentResponseDTO findById(int id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agendamento com ID " + id + " não encontrado."));
        return appointmentMapper.toResponseDTO(appointment);
    }

    @Override
    public List<AppointmentResponseDTO> findByDate(LocalDate date) {
        return appointmentRepository.findByDate(date)
                .stream()
                .map(appointmentMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentResponseDTO> findByDoctor(int doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Médico com ID " + doctorId + " não encontrado."));

        return appointmentRepository.findByDoctor(doctor)
                    .stream()
                    .map(appointmentMapper::toResponseDTO)
                    .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentResponseDTO> findByPatient(int patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente com ID " + patientId + " não encontrado."));

        return appointmentRepository.findByPatient(patient)
                .stream()
                .map(appointmentMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentResponseDTO> findByDoctorAndDate(int doctorId, LocalDate date) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Médico com ID " + doctorId + " não encontrado."));

        return appointmentRepository.findByDoctorAndDate(doctor, date)
                    .stream()
                    .map(appointmentMapper::toResponseDTO)
                    .collect(Collectors.toList());
    }

    @Override
    public AppointmentResponseDTO updateAppointmentStatus(int id, StatusAppointment status, String note) {
        Appointment existingAppointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agendamento com ID " + id + " não encontrado."));

        try {
            existingAppointment.setStatus(status);
            if (note != null && !note.isBlank()){
                existingAppointment.setNote(note);
            }

            Appointment updatedAppointment =  appointmentRepository.save(existingAppointment);
            return appointmentMapper.toResponseDTO(updatedAppointment);
        } catch (Exception e) {
            logger.error("Erro ao tentar atualizar o status do agendamento: " + e.getMessage());
            throw e;
        }
    }
}
