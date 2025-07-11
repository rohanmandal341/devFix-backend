package com.setupsnap.setup_snap_api.service;


import com.setupsnap.setup_snap_api.Dto.LoginRequestDto;
import com.setupsnap.setup_snap_api.Dto.LoginResponseDto;
import com.setupsnap.setup_snap_api.Dto.RegisterRequestDto;
import com.setupsnap.setup_snap_api.model.AuthEntity;
import com.setupsnap.setup_snap_api.repo.AuthRepo;
import com.setupsnap.setup_snap_api.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtUtils utils;
    private final AuthRepo repo;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;


    public void register(RegisterRequestDto dto){
        AuthEntity entity = AuthEntity.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(encoder.encode(dto.getPassword()))
                .roles(Collections.singleton("ROLE_ADMIN"))
                .build();

        repo.save(entity);
    }


    public LoginResponseDto login(LoginRequestDto dto){
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword())
        );

        AuthEntity user  = repo.findByEmail(dto.getEmail()).orElseThrow();

        String token = utils.generateToken(user.getEmail(), user.getRoles());

        return new LoginResponseDto(token, user.getRoles().toString());
    }
}
