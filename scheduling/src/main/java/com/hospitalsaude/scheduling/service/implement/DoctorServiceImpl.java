package com.hospitalsaude.scheduling.service.implement;

import com.hospitalsaude.scheduling.dto.DoctorRequestDTO;
import com.hospitalsaude.scheduling.dto.DoctorResponseDTO;
import com.hospitalsaude.scheduling.mapper.DoctorMapper;
import com.hospitalsaude.scheduling.model.Doctor;
import com.hospitalsaude.scheduling.model.ScheduleDoctor;
import com.hospitalsaude.scheduling.repository.AppointmentRepository;
import com.hospitalsaude.scheduling.repository.DoctorRepository;
import com.hospitalsaude.scheduling.repository.ScheduleDoctorRepository;
import com.hospitalsaude.scheduling.service.interfaces.IDoctorService;
import com.hospitalsaude.scheduling.util.DayWeek;
import com.hospitalsaude.scheduling.util.Specialty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.Normalizer;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class DoctorServiceImpl implements IDoctorService {

    private final DoctorRepository repository;

    private final ScheduleDoctorRepository scheduleDoctorRepository;

    private final AppointmentRepository appointmentRepository;

    private static final Logger logger = LoggerFactory.getLogger(DoctorServiceImpl.class);

    public DoctorServiceImpl(DoctorRepository repository, ScheduleDoctorRepository scheduleDoctorRepository, AppointmentRepository appointmentRepository) {
        this.repository = repository;
        this.scheduleDoctorRepository = scheduleDoctorRepository;
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public DoctorResponseDTO addNewDoctor(DoctorRequestDTO doctorDTO) {
        try {
            Doctor doctorEntity = DoctorMapper.toEntity(doctorDTO);
            Doctor savedEntity = repository.save(doctorEntity);
            return DoctorMapper.toResponseDTO(savedEntity);
        } catch (IllegalArgumentException e) {
            logger.error("Erro ao tentar salvar Doctor: ", e);
        }
        return null;
    }

    @Override
    public DoctorResponseDTO modifyDoctor(int id, DoctorRequestDTO doctorDTO) {
        try {
            Doctor existingDoctor = repository.findById(id).orElse(null);
            if (existingDoctor == null){
                logger.warn("Médico com id " + id + " não encontrado para atualização.");
                return null;
            }

            existingDoctor.setName(doctorDTO.name());
            existingDoctor.setEmail(doctorDTO.email());
            existingDoctor.setPassword(doctorDTO.password());
            existingDoctor.setCpf(doctorDTO.cpf());
            existingDoctor.setPhone(doctorDTO.phone());
            existingDoctor.setCrm(doctorDTO.crm());
            existingDoctor.setSpecialty(doctorDTO.specialty());

            Doctor updateDoctor = repository.save(existingDoctor);
            return DoctorMapper.toResponseDTO(updateDoctor);
        } catch (Exception e) {
            logger.error("Erro ao tentar atualizar Doctor: ", e);
        }
        return null;
    }

    @Override
    public List<DoctorResponseDTO> findAllDoctor() {
        return repository.findAll()
                .stream()
                .map(DoctorMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DoctorResponseDTO findById(int id) {
        Doctor doctor = repository.findById(id).orElse(null);
        return (doctor != null) ? DoctorMapper.toResponseDTO(doctor) : null;
    }

    @Override
    public DoctorResponseDTO findByCrm(int crm) {
        Doctor doctor = repository.findByCrm(crm);
        return (doctor != null) ? DoctorMapper.toResponseDTO(doctor) : null;
    }

    @Override
    public List<DoctorResponseDTO> findBySpecialty(Specialty specialty) {
        return repository.findBySpecialty(specialty)
                .stream()
                .map(DoctorMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }

    @Override
    public Map<DayWeek, List<String>> openingTimes(int doctorId) {
        Doctor doctor = repository.findById(doctorId).orElse(null);
        if (doctor == null){
            logger.warn("Médico com id " + doctorId + " não encontrado para buscar horários.");
            return new HashMap<>();
        }

        List<ScheduleDoctor> scheduleDoctorList = scheduleDoctorRepository.findByDoctor(doctor);
        Map<DayWeek, List<String>> scheduleMap = new HashMap<>();

        for (ScheduleDoctor scheduleDoctor : scheduleDoctorList){
            List<DayWeek> dayWeekList = scheduleDoctor.getDayWeek();
            LocalTime startTime = scheduleDoctor.getStartTime();
            LocalTime endTime = scheduleDoctor.getEndTime();

            for (DayWeek day : dayWeekList){
                List<String> times = scheduleMap.getOrDefault(day, new ArrayList<>());
                LocalTime current = startTime;
                while (current.isBefore(endTime)) {
                    times.add(current.toString());
                    current = current.plusMinutes(30);
                }
                scheduleMap.put(day, times);
            }
        }

        return scheduleMap;
    }

    @Override
    public List<String> findAvailableTimesByDate(int doctorId, LocalDate date) {
        Doctor doctor = repository.findById(doctorId).orElse(null);
        if (doctor == null) {
            logger.warn("Médico com id " + doctorId + " não encontrado para buscar horários disponíveis.");
            return List.of();
        }

        String dayWeek = date.getDayOfWeek()
                .getDisplayName(TextStyle.FULL, Locale.of("pt", "BR"));
        String dayWeekNormalized = Normalizer.normalize(dayWeek, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .replace("-feira", "");
        DayWeek dayWeekEnum = DayWeek.fromString(dayWeekNormalized);

        List<String> appointmentsTimes = appointmentRepository.findByDoctorAndDate(doctor, date)
                .stream()
                .map(appointment -> appointment.getTime().toString())
                .toList();

        Map<DayWeek, List<String>> openingTimes = this.openingTimes(doctorId);
        if (openingTimes.containsKey(dayWeekEnum)){
            List<String> times = openingTimes.get(dayWeekEnum);
            times.removeAll(appointmentsTimes);
            return times;
        }
        return List.of();
    }

    @Override
    public Specialty[] findAllSpeciality() {
        return Specialty.values();
    }
}
