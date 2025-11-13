package com.hospitalsaude.scheduling.controller.auth;

import com.hospitalsaude.scheduling.dto.AuthenticationRequestDTO;
import com.hospitalsaude.scheduling.dto.AuthenticationResponseDTO;
import com.hospitalsaude.scheduling.security.JwtService;
import com.hospitalsaude.scheduling.service.interfaces.IAuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final IAuthenticationService authenticationService;

    public AuthenticationController(IAuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDTO> login(@Valid @RequestBody AuthenticationRequestDTO request){
        AuthenticationResponseDTO response = authenticationService.login(request);
        return  ResponseEntity.ok(response);
    }
}
