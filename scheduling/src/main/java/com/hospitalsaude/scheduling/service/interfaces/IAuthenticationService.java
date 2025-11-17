package com.hospitalsaude.scheduling.service.interfaces;

import com.hospitalsaude.scheduling.dto.AuthenticationRequestDTO;
import com.hospitalsaude.scheduling.dto.AuthenticationResponseDTO;

public interface IAuthenticationService {
    public AuthenticationResponseDTO login(AuthenticationRequestDTO request);
}
