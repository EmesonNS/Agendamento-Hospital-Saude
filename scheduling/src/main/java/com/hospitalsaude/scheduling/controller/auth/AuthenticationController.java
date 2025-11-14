package com.hospitalsaude.scheduling.controller.auth;

import com.hospitalsaude.scheduling.dto.AuthenticationRequestDTO;
import com.hospitalsaude.scheduling.dto.AuthenticationResponseDTO;
import com.hospitalsaude.scheduling.service.interfaces.IAuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticação", description = "Endpoints para login e obtenção de token JWT")
public class AuthenticationController {

    private final IAuthenticationService authenticationService;

    public AuthenticationController(IAuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Operation(summary = "Realizar Login", description = "Autenticar um usuário (Paciente, Médico ou Admin) e retorna um token JWT válido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login realizado com sucesso. Retorna o token."),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos (ex: email mal formatado)."),
            @ApiResponse(responseCode = "401", description = "Falha na autenticação (Email ou senha incorretos).")
    })
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDTO> login(@Valid @RequestBody AuthenticationRequestDTO request){
        AuthenticationResponseDTO response = authenticationService.login(request);
        return  ResponseEntity.ok(response);
    }
}
