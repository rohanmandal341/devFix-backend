package com.setupsnap.setup_snap_api.controller;

import com.setupsnap.setup_snap_api.Dto.GeminiResponse;
import com.setupsnap.setup_snap_api.service.GeminiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/gemini")
public class GeminiController {

    private final GeminiService service;

    @PostMapping
    public ResponseEntity<Mono<GeminiResponse>> create(@RequestBody String prompt){
        return ResponseEntity.ok(service.askGemini(prompt));
    }
}
