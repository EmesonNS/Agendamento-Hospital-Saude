package com.hospitalsaude.scheduling.service.interfaces;

import com.hospitalsaude.scheduling.dto.PatientRequestDTO;
import com.hospitalsaude.scheduling.dto.PatientResponseDTO;

import java.util.List;

public interface IPatientService {
    public PatientResponseDTO addNewPatient(PatientRequestDTO patientDTO);
    public PatientResponseDTO modifyPatient(int id, PatientRequestDTO patientDTO);
    public List<PatientResponseDTO> findAllPatient();
    public PatientResponseDTO findById(int id);
    public PatientResponseDTO findByCpf(String cpf);
    public PatientResponseDTO findByEmail(String email);
    public void deleteById(int id);
}
