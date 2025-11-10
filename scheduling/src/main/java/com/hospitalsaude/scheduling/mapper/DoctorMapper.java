package com.hospitalsaude.scheduling.mapper;

import com.hospitalsaude.scheduling.dto.DoctorRequestDTO;
import com.hospitalsaude.scheduling.dto.DoctorResponseDTO;
import com.hospitalsaude.scheduling.model.Doctor;

public class DoctorMapper {

    public static Doctor toEntity(DoctorRequestDTO dto){
        Doctor entity = new Doctor();

        entity.setName(dto.name());
        entity.setEmail(dto.email());
        entity.setPassword(dto.password());
        entity.setCpf(dto.cpf());
        entity.setPhone(dto.phone());
        entity.setCrm(dto.crm());
        entity.setSpecialty(dto.specialty());

        return entity;
    }

    public static DoctorResponseDTO toResponseDTO(Doctor entity){
        return new DoctorResponseDTO(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getCpf(),
                entity.getPhone(),
                entity.getCrm(),
                entity.getSpecialty()
        );
    }
}
