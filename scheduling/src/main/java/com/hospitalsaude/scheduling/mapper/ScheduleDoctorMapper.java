package com.hospitalsaude.scheduling.mapper;

import com.hospitalsaude.scheduling.dto.ScheduleDoctorRequestDTO;
import com.hospitalsaude.scheduling.dto.ScheduleDoctorResponseDTO;
import com.hospitalsaude.scheduling.model.Doctor;
import com.hospitalsaude.scheduling.model.ScheduleDoctor;
import com.hospitalsaude.scheduling.repository.DoctorRepository;
import org.springframework.stereotype.Component;

@Component
public class ScheduleDoctorMapper {


    private final DoctorRepository doctorRepository;

    public ScheduleDoctorMapper(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public ScheduleDoctor toEntity(ScheduleDoctorRequestDTO dto){
        Doctor doctor = doctorRepository.findById(dto.doctorId()).orElse(null);

        if (doctor == null){
            return null;
        }

        ScheduleDoctor entity = new ScheduleDoctor();
        entity.setDoctor(doctor);
        entity.setDayWeek(dto.dayWeek());
        entity.setStartTime(dto.startTime());
        entity.setEndTime(dto.endTime());

        return entity;
    }

    public ScheduleDoctorResponseDTO toResponseDTO(ScheduleDoctor entity) {
        return new ScheduleDoctorResponseDTO(
                entity.getId(),
                entity.getDoctor().getId(),
                entity.getDoctor().getName(),
                entity.getDayWeek(),
                entity.getStartTime(),
                entity.getEndTime()
        );
    }
}
