package com.example.demo.service.impl;

import com.example.demo.entity.QuotaPlan;
import com.example.demo.repository.QuotaPlanRepository;
import com.example.demo.service.QuotaPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuotaPlanServiceImpl implements QuotaPlanService {

    @Autowired
    private QuotaPlanRepository quotaPlanRepository;

    @Override
    public List<QuotaPlan> getAllPlans() {
        return quotaPlanRepository.findAll();
    }

    @Override
    public QuotaPlan getPlanById(Long id) {
        return quotaPlanRepository.findById(id).orElse(null);
    }

    @Override
    public QuotaPlan createPlan(QuotaPlan plan) {
        return quotaPlanRepository.save(plan);
    }

    @Override
    public QuotaPlan updatePlan(Long id, QuotaPlan plan) {
        Optional<QuotaPlan> existing = quotaPlanRepository.findById(id);
        if (existing.isPresent()) {
            QuotaPlan p = existing.get();
            p.setName(plan.getName());
            p.setDescription(plan.getDescription());
            p.setActive(plan.isActive());
            return quotaPlanRepository.save(p);
        }
        return null;
    }

    @Override
    public void deletePlan(Long id) {
        quotaPlanRepository.deleteById(id);
    }
}
