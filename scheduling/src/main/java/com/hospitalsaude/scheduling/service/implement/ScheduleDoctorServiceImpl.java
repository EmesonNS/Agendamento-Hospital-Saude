package com.hospitalsaude.scheduling.service.implement;

import com.hospitalsaude.scheduling.dto.ScheduleDoctorRequestDTO;
import com.hospitalsaude.scheduling.dto.ScheduleDoctorResponseDTO;
import com.hospitalsaude.scheduling.exception.ResourceNotFoundException;
import com.hospitalsaude.scheduling.mapper.ScheduleDoctorMapper;
import com.hospitalsaude.scheduling.model.Doctor;
import com.hospitalsaude.scheduling.model.ScheduleDoctor;
import com.hospitalsaude.scheduling.repository.DoctorRepository;
import com.hospitalsaude.scheduling.repository.ScheduleDoctorRepository;
import com.hospitalsaude.scheduling.service.interfaces.IScheduleDoctorService;
import com.hospitalsaude.scheduling.util.DayWeek;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ScheduleDoctorServiceImpl implements IScheduleDoctorService {

    private final ScheduleDoctorRepository scheduleDoctorRepository;
    private final DoctorRepository doctorRepository;
    private final ScheduleDoctorMapper scheduleDoctorMapper;

    private static final Logger logger = LoggerFactory.getLogger(ScheduleDoctorServiceImpl.class);

    public ScheduleDoctorServiceImpl(ScheduleDoctorRepository scheduleDoctorRepository, DoctorRepository doctorRepository, ScheduleDoctorMapper scheduleDoctorMapper) {
        this.scheduleDoctorRepository = scheduleDoctorRepository;
        this.doctorRepository = doctorRepository;
        this.scheduleDoctorMapper = scheduleDoctorMapper;
    }

    @Override
    public ScheduleDoctorResponseDTO addNewSchedule(ScheduleDoctorRequestDTO scheduleDoctorDTO) {
        try {
            ScheduleDoctor newSchedule = scheduleDoctorMapper.toEntity(scheduleDoctorDTO);

            ScheduleDoctor savedSchedule = scheduleDoctorRepository.save(newSchedule);
            return scheduleDoctorMapper.toResponseDTO(savedSchedule);
        } catch (IllegalArgumentException e) {
            logger.error("Erro ao tentar salvar Schedule: ", e);
            throw e;
        }
    }

    @Override
    public ScheduleDoctorResponseDTO modifySchedule(int id, ScheduleDoctorRequestDTO scheduleDoctorDTO) {
        ScheduleDoctor existingSchedule = scheduleDoctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agenda com ID " + id + " não encontrada."));

        Doctor doctor = doctorRepository.findById(scheduleDoctorDTO.doctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Médico com ID " + scheduleDoctorDTO.doctorId() + " não encontrado."));

        try {
            existingSchedule.setDoctor(doctor);
            existingSchedule.setDayWeek(scheduleDoctorDTO.dayWeek());
            existingSchedule.setStartTime(scheduleDoctorDTO.startTime());
            existingSchedule.setEndTime(scheduleDoctorDTO.endTime());

            ScheduleDoctor updateSchedule = scheduleDoctorRepository.save(existingSchedule);
            return scheduleDoctorMapper.toResponseDTO(updateSchedule);
        } catch (Exception e) {
            logger.error("Erro ao tentar atualizar Schedule: ", e);
            throw e;
        }
    }

    @Override
    public List<ScheduleDoctorResponseDTO> findAllSchedule() {
        return scheduleDoctorRepository.findAll()
                .stream()
                .map(scheduleDoctorMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ScheduleDoctorResponseDTO findById(int id) {
        ScheduleDoctor schedule = scheduleDoctorRepository.findById(id).orElse(null);
        return (schedule != null) ? scheduleDoctorMapper.toResponseDTO(schedule) : null;
    }

    @Override
    public List<ScheduleDoctorResponseDTO> findByDayWeek(DayWeek dayWeek) {
        return scheduleDoctorRepository.findByDayWeekContaining(dayWeek)
                .stream()
                .map(scheduleDoctorMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ScheduleDoctorResponseDTO> findByDoctor(int doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Médico com ID " + doctorId + " não encontrado."));

        return scheduleDoctorRepository.findByDoctor(doctor)
                .stream()
                .map(scheduleDoctorMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(int id) {
        if (!scheduleDoctorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Agenda", "com ID " + id + " não encontrada para exclusão");
        }
        scheduleDoctorRepository.deleteById(id);
    }
}
