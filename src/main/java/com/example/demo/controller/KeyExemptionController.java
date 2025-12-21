package com.example.demo.controller;

import com.example.demo.dto.KeyExemptionDto;
import com.example.demo.service.KeyExemptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/key-exemptions")
public class KeyExemptionController {

    private final KeyExemptionService exemptionService;

    public KeyExemptionController(KeyExemptionService exemptionService) {
        this.exemptionService = exemptionService;
    }

    @PostMapping
    public ResponseEntity<KeyExemptionDto> createExemption(@RequestBody KeyExemptionDto dto) {
        KeyExemptionDto response = exemptionService.createExemption(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<KeyExemptionDto> updateExemption(@PathVariable Long id, @RequestBody KeyExemptionDto dto) {
        KeyExemptionDto response = exemptionService.updateExemption(id, dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/key/{keyId}")
    public ResponseEntity<KeyExemptionDto> getExemptionByKey(@PathVariable Long keyId) {
        KeyExemptionDto response = exemptionService.getExemptionByKey(keyId);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<KeyExemptionDto>> getAllExemptions() {
        List<KeyExemptionDto> response = exemptionService.getAllExemptions();
        return ResponseEntity.ok(response);
    }
}
