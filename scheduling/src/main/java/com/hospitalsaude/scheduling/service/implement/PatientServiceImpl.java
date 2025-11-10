package com.hospitalsaude.scheduling.service.implement;

import com.hospitalsaude.scheduling.dto.PatientRequestDTO;
import com.hospitalsaude.scheduling.dto.PatientResponseDTO;
import com.hospitalsaude.scheduling.mapper.PatientMapper;
import com.hospitalsaude.scheduling.model.Patient;
import com.hospitalsaude.scheduling.repository.PatientRepository;
import com.hospitalsaude.scheduling.service.interfaces.IPatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PatientServiceImpl implements IPatientService {

    private final PatientRepository repository;

    private static final Logger logger = LoggerFactory.getLogger(PatientServiceImpl.class);

    public PatientServiceImpl(PatientRepository repository) {
        this.repository = repository;
    }

    @Override
    public PatientResponseDTO addNewPatient(PatientRequestDTO patientDTO) {
        try {
            Patient patientEntity = PatientMapper.toEntity(patientDTO);
            Patient savedPatient = repository.save(patientEntity);
            return PatientMapper.toResponseDTO(savedPatient);
        } catch (IllegalArgumentException e) {
            logger.error("Erro ao tentar salvar Patient: ", e);
        }
        return null;
    }

    @Override
    public PatientResponseDTO modifyPatient(int id, PatientRequestDTO patientDTO) {
        try {
            Patient existingPatient = repository.findById(id).orElse(null);
            if (existingPatient == null){
                logger.warn("Paciente com id " + id + "não encontrado para atualização.");
                return null;
            }

            existingPatient.setName(patientDTO.name());
            existingPatient.setEmail(patientDTO.email());
            existingPatient.setPassword(patientDTO.password()); // (Ainda em texto plano, cuidaremos disso no passo de segurança)
            existingPatient.setCpf(patientDTO.cpf());
            existingPatient.setPhone(patientDTO.phone());
            existingPatient.setDateBirth(patientDTO.dateBirth());
            existingPatient.setGender(patientDTO.gender());
            existingPatient.setBloodType(patientDTO.bloodType());
            existingPatient.setNote(patientDTO.note());

            Patient updatedEntity = repository.save(existingPatient);
            return PatientMapper.toResponseDTO(updatedEntity);
        }catch (IllegalArgumentException e){
            logger.error("Erro ao tentar atualizar Patient: ", e);
        }
        return null;
    }

    @Override
    public List<PatientResponseDTO> findAllPatient() {
        return repository.findAll()
                .stream()
                .map(PatientMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PatientResponseDTO findById(int id) {
        Patient patient = repository.findById(id).orElse(null);
        return (patient != null) ? PatientMapper.toResponseDTO(patient) : null;
    }

    @Override
    public PatientResponseDTO findByCpf(String cpf) {
        Patient patient = repository.findByCpf(cpf);
        return (patient != null) ? PatientMapper.toResponseDTO(patient) : null;
    }

    @Override
    public PatientResponseDTO findByEmail(String email) {
        Patient patient = repository.findByEmail(email);
        return (patient != null) ? PatientMapper.toResponseDTO(patient) : null;
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }
}
