package com.example.demo.controller;

import com.example.demo.dto.QuotaPlanDto;
import com.example.demo.service.QuotaPlanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quota-plans")
public class QuotaPlanController {

    private final QuotaPlanService quotaPlanService;

    public QuotaPlanController(QuotaPlanService quotaPlanService) {
        this.quotaPlanService = quotaPlanService;
    }

    @PostMapping
    public ResponseEntity<QuotaPlanDto> createQuotaPlan(@RequestBody QuotaPlanDto dto) {
        QuotaPlanDto response = quotaPlanService.createQuotaPlan(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuotaPlanDto> updateQuotaPlan(@PathVariable Long id, @RequestBody QuotaPlanDto dto) {
        QuotaPlanDto response = quotaPlanService.updateQuotaPlan(id, dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuotaPlanDto> getQuotaPlanById(@PathVariable Long id) {
        QuotaPlanDto response = quotaPlanService.getQuotaPlanById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<QuotaPlanDto>> getAllPlans() {
        List<QuotaPlanDto> response = quotaPlanService.getAllPlans();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateQuotaPlan(@PathVariable Long id) {
        quotaPlanService.deactivateQuotaPlan(id);
        return ResponseEntity.noContent().build();
    }
}
