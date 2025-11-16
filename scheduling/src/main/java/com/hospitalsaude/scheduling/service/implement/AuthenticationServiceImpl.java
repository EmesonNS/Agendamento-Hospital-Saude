package com.hospitalsaude.scheduling.service.implement;

import com.hospitalsaude.scheduling.dto.AuthenticationRequestDTO;
import com.hospitalsaude.scheduling.dto.AuthenticationResponseDTO;
import com.hospitalsaude.scheduling.security.JwtService;
import com.hospitalsaude.scheduling.service.interfaces.IAuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;

    public AuthenticationServiceImpl(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
    }

    @Override
    public AuthenticationResponseDTO login(AuthenticationRequestDTO request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.email(),
                            request.password()
                    )
            );
        }catch (AuthenticationException e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciais inválidas", e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.email());
        final String token = jwtService.generateToken(userDetails);

        return new AuthenticationResponseDTO(token);
    }
}
