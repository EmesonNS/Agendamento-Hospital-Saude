package com.hospitalsaude.scheduling.mapper;

import com.hospitalsaude.scheduling.dto.PatientRequestDTO;
import com.hospitalsaude.scheduling.dto.PatientResponseDTO;
import com.hospitalsaude.scheduling.model.Patient;

public class PatientMapper {

    public static Patient toEntity(PatientRequestDTO dto){
        Patient entity = new Patient();

        entity.setName(dto.name());
        entity.setEmail(dto.email());
        entity.setPassword(dto.password());
        entity.setCpf(dto.cpf());
        entity.setPhone(dto.phone());
        entity.setDateBirth(dto.dateBirth());
        entity.setGender(dto.gender());
        entity.setBloodType(dto.bloodType());
        entity.setNote(dto.note());

        return entity;
    }

    public static PatientResponseDTO toResponseDTO(Patient entity){
        return new PatientResponseDTO(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getCpf(),
                entity.getPhone(),
                entity.getDateBirth(),
                entity.getGender(),
                entity.getBloodType(),
                entity.getNote()
        );
    }
}
