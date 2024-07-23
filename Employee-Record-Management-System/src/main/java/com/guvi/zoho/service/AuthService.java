package com.guvi.zoho.service;

import com.guvi.zoho.dto.JwtAuthResponse;
import com.guvi.zoho.dto.LoginRequestDto;
import com.guvi.zoho.dto.RegisterDto;

public interface AuthService {
    String register(RegisterDto registerDto);

    JwtAuthResponse login(LoginRequestDto loginRequestDto);
}

