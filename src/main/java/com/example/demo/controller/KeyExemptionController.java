package com.example.demo.controller;

import com.example.demo.model.KeyExemption;
import com.example.demo.service.KeyExemptionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/key-exemptions")
@Tag(name = "Key Exemptions", description = "Manage API key exemptions")
public class KeyExemptionController {

    private final KeyExemptionService exemptionService;

    public KeyExemptionController(KeyExemptionService exemptionService) {
        this.exemptionService = exemptionService;
    }

    @PostMapping
    public ResponseEntity<KeyExemption> createExemption(@RequestBody KeyExemption exemption) {
        return ResponseEntity.ok(exemptionService.createExemption(exemption));
    }

    @PutMapping("/{id}")
    public ResponseEntity<KeyExemption> updateExemption(@PathVariable Long id, @RequestBody KeyExemption exemption) {
        return ResponseEntity.ok(exemptionService.updateExemption(id, exemption));
    }

    @GetMapping("/key/{keyId}")
    public ResponseEntity<KeyExemption> getExemptionForKey(@PathVariable Long keyId) {
        return ResponseEntity.ok(exemptionService.getExemptionByKey(keyId));
    }

    @GetMapping
    public ResponseEntity<List<KeyExemption>> getAllExemptions() {
        return ResponseEntity.ok(exemptionService.getAllExemptions());
    }
}
