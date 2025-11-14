package com.hospitalsaude.scheduling.exception;

import com.hospitalsaude.scheduling.dto.ErrorResponseDTO;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.naming.AuthenticationException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationException(MethodArgumentNotValidException exception){
        Map<String, String> errors = new HashMap<>();

        exception.getBindingResult().getAllErrors().forEach((error) ->{
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ErrorResponseDTO errorResponse = new ErrorResponseDTO("Erro de validação", errors);

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponseDTO> handleAuthenticationException(AuthenticationException exception){
        Map<String, String> error = new HashMap<>();
        error.put("autenticação", "Email ou senha inválidos");

        ErrorResponseDTO errorResponse = new ErrorResponseDTO("Falha na autenticação", error);

        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponseDTO> handleDataIntegrityViolation(DataIntegrityViolationException exception){
        Map<String, String> error = new HashMap<>();

        if (exception.getCause() != null && exception.getCause().getMessage().contains("uq_appointment_slot")){
            error.put("agendamento", "Este horário já foi reservado por outro paciente.");
        } else if (exception.getCause() != null && exception.getCause().getMessage().contains("uk_")){
            error.put("conflito", "Já existe um registro com este CPF ou Email");
        } else {
            error.put("banco_de_dados", "Erro de integridade de dados");
        }

        ErrorResponseDTO errorResponse = new ErrorResponseDTO("Conflito de dados", error);

        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleResourceNotFoundException(ResourceNotFoundException exception){
        Map<String, String> error = new HashMap<>();
        error.put("recurso", exception.getMessage());

        ErrorResponseDTO errorResponse = new ErrorResponseDTO("Recurso não encontrado", error);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
