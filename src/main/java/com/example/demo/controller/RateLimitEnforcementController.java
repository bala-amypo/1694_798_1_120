package com.example.demo.controller;

import com.example.demo.dto.RateLimitEnforcementDto;
import com.example.demo.service.RateLimitEnforcementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enforcements")
public class RateLimitEnforcementController {

    private final RateLimitEnforcementService enforcementService;

    public RateLimitEnforcementController(RateLimitEnforcementService enforcementService) {
        this.enforcementService = enforcementService;
    }

    @PostMapping
    public ResponseEntity<RateLimitEnforcementDto> createEnforcement(@RequestBody RateLimitEnforcementDto dto) {
        RateLimitEnforcementDto response = enforcementService.createEnforcement(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RateLimitEnforcementDto> getEnforcementById(@PathVariable Long id) {
        RateLimitEnforcementDto response = enforcementService.getEnforcementById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/key/{keyId}")
    public ResponseEntity<List<RateLimitEnforcementDto>> getEnforcementsForKey(@PathVariable Long keyId) {
        List<RateLimitEnforcementDto> response = enforcementService.getEnforcementsForKey(keyId);
        return ResponseEntity.ok(response);
    }
}
