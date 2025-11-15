package com.hospitalsaude.scheduling.service.implement;

import com.hospitalsaude.scheduling.dto.PatientRequestDTO;
import com.hospitalsaude.scheduling.dto.PatientResponseDTO;
import com.hospitalsaude.scheduling.exception.ResourceNotFoundException;
import com.hospitalsaude.scheduling.mapper.PatientMapper;
import com.hospitalsaude.scheduling.model.Patient;
import com.hospitalsaude.scheduling.repository.PatientRepository;
import com.hospitalsaude.scheduling.service.interfaces.IPatientService;
import com.hospitalsaude.scheduling.util.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PatientServiceImpl implements IPatientService {

    private final PatientRepository repository;
    private final PasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(PatientServiceImpl.class);

    public PatientServiceImpl(PatientRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public PatientResponseDTO addNewPatient(PatientRequestDTO patientDTO) {
        try {
            Patient patientEntity = PatientMapper.toEntity(patientDTO);

            String hashedPassword = passwordEncoder.encode(patientDTO.password());
            patientEntity.setPassword(hashedPassword);
            patientEntity.setRole(Role.ROLE_PATIENT);

            Patient savedPatient = repository.save(patientEntity);
            return PatientMapper.toResponseDTO(savedPatient);
        } catch (IllegalArgumentException e) {
            logger.error("Erro ao tentar salvar Patient: ", e);
            throw e;
        }
    }

    @Override
    public PatientResponseDTO modifyPatient(int id, PatientRequestDTO patientDTO) {
        Patient existingPatient = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente com ID " + id + " não encontrado."));

        try {
            existingPatient.setName(patientDTO.name());
            existingPatient.setEmail(patientDTO.email());

            String hashedPassword = passwordEncoder.encode(patientDTO.password());
            existingPatient.setPassword(hashedPassword);

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
            throw e;
        }
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
        Patient patient = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente com ID " + id + " não encontrado."));
        return PatientMapper.toResponseDTO(patient);
    }

    @Override
    public PatientResponseDTO findByCpf(String cpf) {
        Patient patient = repository.findByCpf(cpf);
        if (patient == null){
            throw new ResourceNotFoundException("Paciente com CPF " + cpf + " não encontrado.");
        }
        return PatientMapper.toResponseDTO(patient);
    }

    @Override
    public PatientResponseDTO findByEmail(String email) {
        Patient patient = repository.findByEmail(email);
        if (patient == null){
            throw new ResourceNotFoundException("Paciente com Email " + email + " não encontrado.");
        }
        return PatientMapper.toResponseDTO(patient);
    }

    @Override
    public void deleteById(int id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Paciente com ID " + id + " não encontrado para exclusão.");
        }
        repository.deleteById(id);
    }
}
