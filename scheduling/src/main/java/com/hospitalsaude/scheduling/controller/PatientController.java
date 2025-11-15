package com.hospitalsaude.scheduling.controller;

import com.hospitalsaude.scheduling.dto.PatientRequestDTO;
import com.hospitalsaude.scheduling.dto.PatientResponseDTO;
import com.hospitalsaude.scheduling.service.interfaces.IPatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
@Tag(name = "Pacientes", description = "Gerenciamento de pacientes do hospital")
public class PatientController {

    private final IPatientService service;

    public PatientController(IPatientService service) {
        this.service = service;
    }

    @Operation(summary = "Cadastrar novo paciente", description = "Criar um novo registro de paciente no sistema,. Aberto ao público.")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "201", description = "Paciente criado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro de validação (ex: CPF inválido, campos vazios)."),
            @ApiResponse(responseCode = "409", description = "Conflito: Email ou CPF já cadastrados.")
    })
    @PostMapping
    public ResponseEntity<PatientResponseDTO> addNew(
            @Valid @RequestBody PatientRequestDTO patientDTO){
        PatientResponseDTO result = service.addNewPatient(patientDTO);
        return ResponseEntity.status(201).body(result);
    }

    @Operation(summary = "Listar todos os pacientes", description = "Retorna uma lista completa. Requer ROLE_ADMIN ou ROLE_DOCTOR.")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> findAll(){
        return ResponseEntity.ok(service.findAllPatient());
    }

    @Operation(summary = "Buscar paciente por ID", description = "Requer ROLE_ADMIN ou ROLE_DOCTOR.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Paciente encontrado."),
            @ApiResponse(responseCode = "404", description = "Paciente não encontrado.")
    })
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping(value = "/search", params = "id")
    public ResponseEntity<PatientResponseDTO> findById(@RequestParam(name = "id") int id){
        PatientResponseDTO result = service.findById(id);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Buscar paciente por CPF")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping(value = "/search", params = "cpf")
    public ResponseEntity<PatientResponseDTO> findByCpf(@RequestParam(name = "cpf") String cpf){
        PatientResponseDTO result = service.findByCpf(cpf);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Buscar paciente por Email")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping(value = "/search", params = "email")
    public ResponseEntity<PatientResponseDTO> findByEmail(@RequestParam(name = "email") String email){
        PatientResponseDTO result = service.findByEmail(email);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Atualizar dados do paciente", description = "Requer ROLE_ADMIN ou ROLE_DOCTOR.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dados atualizados."),
            @ApiResponse(responseCode = "404", description = "Paciente não encontrado.")
    })
    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> alterPatient(
            @PathVariable int id,
            @Valid @RequestBody PatientRequestDTO patientDTO){
        PatientResponseDTO result = service.modifyPatient(id, patientDTO);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Excluir paciente", description = "Remove um registro do sistema. Requer ROLE_ADMIN.")
    @ApiResponse(responseCode = "404", description = "Paciente não encontrado.")
    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id){
            service.deleteById(id);
            return ResponseEntity.ok().build();
    }
}
