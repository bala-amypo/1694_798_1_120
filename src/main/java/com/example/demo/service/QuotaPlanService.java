package com.example.demo.service;

import com.example.demo.entity.QuotaPlan;

import java.util.List;

public interface QuotaPlanService {
    List<QuotaPlan> getAllPlans();
    QuotaPlan getPlanById(Long id);
    QuotaPlan createPlan(QuotaPlan plan);
    QuotaPlan updatePlan(Long id, QuotaPlan plan);
    void deletePlan(Long id);
}
