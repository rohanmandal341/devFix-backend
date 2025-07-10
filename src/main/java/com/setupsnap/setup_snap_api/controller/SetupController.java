package com.setupsnap.setup_snap_api.controller;


import com.setupsnap.setup_snap_api.Dto.SetupDto;
import com.setupsnap.setup_snap_api.service.SetupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RequestMapping("/api/setup")
@RequiredArgsConstructor
@RestController
public class SetupController {

    private final SetupService service;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<SetupDto> add(@RequestBody SetupDto dto) {
        return ResponseEntity.ok(service.createSetup(dto));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/search")
    public ResponseEntity<List<SetupDto>> search(@RequestParam String keyword) {
        return ResponseEntity.ok(service.searchSetup(keyword));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<SetupDto>> videos() {
        return ResponseEntity.ok(service.getSetup());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<SetupDto> updateVideo(@PathVariable Long id, @RequestBody SetupDto dto) {
        return ResponseEntity.ok(service.updateSetup(id, dto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.deleteSetup(id);
        return ResponseEntity.ok("successfully deleted id");
    }
}
