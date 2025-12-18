package com.example.demo.controller;

import com.example.demo.model.RateLimitEnforcement;
import com.example.demo.service.RateLimitEnforcementService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enforcements")
@Tag(name = "Rate Limit Enforcements", description = "Track API rate limit enforcement events")
public class RateLimitEnforcementController {

    private final RateLimitEnforcementService enforcementService;

    public RateLimitEnforcementController(RateLimitEnforcementService enforcementService) {
        this.enforcementService = enforcementService;
    }

    @PostMapping
    public ResponseEntity<RateLimitEnforcement> createEnforcement(@RequestBody RateLimitEnforcement enforcement) {
        return ResponseEntity.ok(enforcementService.createEnforcement(enforcement));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RateLimitEnforcement> getEnforcementById(@PathVariable Long id) {
        return ResponseEntity.ok(enforcementService.getEnforcementById(id));
    }

    @GetMapping("/key/{keyId}")
    public ResponseEntity<List<RateLimitEnforcement>> getEnforcementsForKey(@PathVariable Long keyId) {
        return ResponseEntity.ok(enforcementService.getEnforcementsForKey(keyId));
    }
}
