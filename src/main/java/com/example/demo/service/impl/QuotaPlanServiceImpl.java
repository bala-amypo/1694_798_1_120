package com.example.demo.service.impl;

import com.example.demo.entity.QuotaPlan;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.QuotaPlanRepository;
import com.example.demo.service.QuotaPlanService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuotaPlanServiceImpl implements QuotaPlanService {

    private final QuotaPlanRepository repository;

    public QuotaPlanServiceImpl(QuotaPlanRepository repository) {
        this.repository = repository;
    }

    @Override
    public QuotaPlan createQuotaPlan(QuotaPlan plan) {
        if (plan.getDailyLimit() == null || plan.getDailyLimit() <= 0) {
            throw new BadRequestException("Daily limit must be > 0");
        }
        return repository.save(plan);
    }

    @Override
    public QuotaPlan updateQuotaPlan(Long id, QuotaPlan plan) {
        QuotaPlan existing = getQuotaPlanById(id);
        existing.setDailyLimit(plan.getDailyLimit());
        existing.setDescription(plan.getDescription());
        return repository.save(existing);
    }

    @Override
    public QuotaPlan getQuotaPlanById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Quota plan not found"));
    }

    @Override
    public List<QuotaPlan> getAllPlans() {
        return repository.findAll();
    }

    @Override
    public void deactivateQuotaPlan(Long id) {
        QuotaPlan plan = getQuotaPlanById(id);
        plan.setActive(false);
        repository.save(plan);
    }
}
