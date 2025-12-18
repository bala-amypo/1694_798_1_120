package com.example.demo.controller;

import com.example.demo.entity.QuotaPlan;
import com.example.demo.service.QuotaPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quotaplans")
public class QuotaPlanController {

    @Autowired
    private QuotaPlanService quotaPlanService;

    @GetMapping
    public List<QuotaPlan> getAllPlans() {
        return quotaPlanService.getAllPlans();
    }

    @GetMapping("/{id}")
    public QuotaPlan getPlan(@PathVariable Long id) {
        return quotaPlanService.getPlanById(id);
    }

    @PostMapping
    public QuotaPlan createPlan(@RequestBody QuotaPlan plan) {
        return quotaPlanService.createPlan(plan);
    }

    @PutMapping("/{id}")
    public QuotaPlan updatePlan(@PathVariable Long id, @RequestBody QuotaPlan plan) {
        return quotaPlanService.updatePlan(id, plan);
    }

    @DeleteMapping("/{id}")
    public void deletePlan(@PathVariable Long id) {
        quotaPlanService.deletePlan(id);
    }
}
