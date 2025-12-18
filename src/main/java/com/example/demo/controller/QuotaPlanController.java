package com.example.demo.controller;

import com.example.demo.entity.QuotaPlan;
import com.example.demo.service.QuotaPlanService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quota-plans")
@Tag(name = "Quota Plans", description = "Manage quota plans")
public class QuotaPlanController {

    private final QuotaPlanService quotaPlanService;

    public QuotaPlanController(QuotaPlanService quotaPlanService) {
        this.quotaPlanService = quotaPlanService;
    }

    @PostMapping
    public ResponseEntity<QuotaPlan> createPlan(@RequestBody QuotaPlan plan) {
        return ResponseEntity.ok(quotaPlanService.createQuotaPlan(plan));
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuotaPlan> updatePlan(@PathVariable Long id, @RequestBody QuotaPlan plan) {
        return ResponseEntity.ok(quotaPlanService.updateQuotaPlan(id, plan));
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuotaPlan> getPlanById(@PathVariable Long id) {
        return ResponseEntity.ok(quotaPlanService.getQuotaPlanById(id));
    }

    @GetMapping
    public ResponseEntity<List<QuotaPlan>> getAllPlans() {
        return ResponseEntity.ok(quotaPlanService.getAllPlans());
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<QuotaPlan> deactivatePlan(@PathVariable Long id) {
        return ResponseEntity.ok(quotaPlanService.deactivateQuotaPlan(id));
    }
}
