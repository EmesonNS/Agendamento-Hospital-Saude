package com.hospitalsaude.scheduling.mapper;

import com.hospitalsaude.scheduling.dto.AppointmentRequestDTO;
import com.hospitalsaude.scheduling.dto.AppointmentResponseDTO;
import com.hospitalsaude.scheduling.dto.DoctorResponseDTO;
import com.hospitalsaude.scheduling.dto.PatientResponseDTO;
import com.hospitalsaude.scheduling.model.Appointment;
import com.hospitalsaude.scheduling.model.Doctor;
import com.hospitalsaude.scheduling.model.Patient;
import com.hospitalsaude.scheduling.repository.DoctorRepository;
import com.hospitalsaude.scheduling.repository.PatientRepository;
import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper {

    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public AppointmentMapper(DoctorRepository doctorRepository, PatientRepository patientRepository) {
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    public Appointment toEntity(AppointmentRequestDTO dto){
        Doctor doctor = doctorRepository.findById(dto.doctorId()).orElse(null);
        Patient patient = patientRepository.findById(dto.patientId()).orElse(null);

        if (doctor == null || patient == null){
            return null;
        }

        Appointment entity = new Appointment();
        entity.setDoctor(doctor);
        entity.setPatient(patient);
        entity.setDate(dto.date());
        entity.setTime(dto.time());
        entity.setType(dto.type());
        entity.setNote(dto.note());

        return entity;
    }

    public AppointmentResponseDTO toResponseDTO(Appointment entity){
        DoctorResponseDTO doctorDTO = DoctorMapper.toResponseDTO(entity.getDoctor());
        PatientResponseDTO patientDTO = PatientMapper.toResponseDTO(entity.getPatient());

        return new AppointmentResponseDTO(
                entity.getId(),
                doctorDTO,
                patientDTO,
                entity.getDateAppointment(),
                entity.getDate(),
                entity.getTime(),
                entity.getStatus(),
                entity.getType(),
                entity.getNote()
        );
    }
}
