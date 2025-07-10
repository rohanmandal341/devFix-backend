package com.setupsnap.setup_snap_api.controller;


import com.setupsnap.setup_snap_api.Dto.LoginRequestDto;
import com.setupsnap.setup_snap_api.Dto.LoginResponseDto;
import com.setupsnap.setup_snap_api.Dto.RegisterRequestDto;
import com.setupsnap.setup_snap_api.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService service;

    @PostMapping("/register")
    public ResponseEntity<String> create(@RequestBody RegisterRequestDto dto){
        service.register(dto);
        return ResponseEntity.ok("registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto dto){
        return ResponseEntity.ok(service.login(dto));
    }
}
