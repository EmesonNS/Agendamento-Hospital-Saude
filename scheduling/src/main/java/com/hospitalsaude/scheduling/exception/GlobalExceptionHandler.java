package com.hospitalsaude.scheduling.exception;

import com.hospitalsaude.scheduling.dto.ErrorResponseDTO;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<ErrorResponseDTO> handleBusinessRuleException(BusinessRuleException exception){
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("codigo_erro", exception.getErrorCode());
        errorDetails.put("mensagem_usuario", exception.getUserMessage());

        ErrorResponseDTO errorResponse = new ErrorResponseDTO("Violação de regra de negócio", errorDetails);
        return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(AppointmentConflictException.class)
    public ResponseEntity<ErrorResponseDTO> handleAppointmentConflictException(AppointmentConflictException exception){
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("codigo_erro", exception.getErrorCode());
        errorDetails.put("mensagem_usuario", exception.getUserMessage());
        errorDetails.put("conflito", "agendamento");

        ErrorResponseDTO errorResponse = new ErrorResponseDTO("Conflito de agendamento", errorDetails);
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(AuthenticationFailedException.class)
    public ResponseEntity<ErrorResponseDTO> handleAuthenticationFailedException(AuthenticationFailedException exception){
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("codigo_erro", exception.getErrorCode());
        errorDetails.put("mensagem_usuario", exception.getUserMessage());

        ErrorResponseDTO errorResponse = new ErrorResponseDTO("Falha na autenticação", errorDetails);
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(DataValidationException.class)
    public ResponseEntity<ErrorResponseDTO> handleDataValidationException(DataValidationException exception){
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("codigo_erro", exception.getErrorCode());
        errorDetails.put("mensagem_usuario", exception.getUserMessage());
        errorDetails.put("erros_validacao", exception.getValidationErrors());

        ErrorResponseDTO errorResponse = new ErrorResponseDTO("Erro de validação de dados", errorDetails);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponseDTO> handleResponseStatusException(ResponseStatusException exception){
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("codigo_status", exception.getStatusCode().value());
        errorDetails.put("motivo", exception.getReason());

        ErrorResponseDTO errorResponse = new ErrorResponseDTO("Erro de resposta", errorDetails);
        return new ResponseEntity<>(errorResponse, exception.getStatusCode());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationException(MethodArgumentNotValidException exception){
        Map<String, Object> errorDetails = new HashMap<>();
        Map<String, String> errors = new HashMap<>();

        exception.getBindingResult().getAllErrors().forEach((error) ->{
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        errorDetails.put("erros_validacao", errors);

        ErrorResponseDTO errorResponse = new ErrorResponseDTO("Erro de validação de campos", errorDetails);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGenericException(Exception exception){
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("tipo_erro", exception.getClass().getSimpleName());
        errorDetails.put("mensagem_interna", exception.getMessage());

        ErrorResponseDTO errorResponse = new ErrorResponseDTO("Erro interno do servidor", errorDetails);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponseDTO> handleDataIntegrityViolation(DataIntegrityViolationException exception){
        Map<String, Object> errorDetails = new HashMap<>();

        if (exception.getCause() != null && exception.getCause().getMessage().contains("uq_appointment_slot")){
            errorDetails.put("conflito", "agendamento");
            errorDetails.put("mensagem_usuario", "Este horário já foi reservado por outro paciente.");
        } else if (exception.getCause() != null && exception.getCause().getMessage().contains("uk_")){
            errorDetails.put("conflito", "dado_unico");
            errorDetails.put("mensagem_usuario", "Já existe um registro com este CPF ou Email");
        } else if (exception.getCause().getMessage().contains("Cannot delete or update a parent row: a foreign key constraint fails")) {
            errorDetails.put("conflito_referencia", "Este registro não pode ser excluído pois está sendo usado por outro (ex: uma consulta).");
        } else {
            errorDetails.put("conflito", "integridade_dados");
            errorDetails.put("mensagem_usuario", "Erro de integridade de dados no banco de dados");
        }

        ErrorResponseDTO errorResponse = new ErrorResponseDTO("Violação de integridade de dados", errorDetails);
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleResourceNotFoundException(ResourceNotFoundException exception){
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("recurso", exception.getMessage());

        ErrorResponseDTO errorResponse = new ErrorResponseDTO("Recurso não encontrado", errorDetails);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
