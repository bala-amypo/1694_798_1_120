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

    private final QuotaPlanRepository repo;

    public QuotaPlanServiceImpl(QuotaPlanRepository repo) {
        this.repo = repo;
    }

    @Override
    public QuotaPlan createQuotaPlan(QuotaPlan p) {
        if (p.getDailyLimit() == null || p.getDailyLimit() <= 0) {
            throw new BadRequestException("Invalid daily limit");
        }
        return repo.save(p);
    }

    @Override
    public QuotaPlan updateQuotaPlan(Long id, QuotaPlan p) {
        QuotaPlan existing = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("QuotaPlan not found"));

        if (p.getDailyLimit() <= 0) {
            throw new BadRequestException("Invalid daily limit");
        }

        existing.setPlanName(p.getPlanName());
        existing.setDailyLimit(p.getDailyLimit());
        return repo.save(existing);
    }

    @Override
    public QuotaPlan getQuotaPlanById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("QuotaPlan not found"));
    }

    @Override
    public List<QuotaPlan> getAllPlans() {
        return repo.findAll();
    }

    @Override
    public void deactivateQuotaPlan(Long id) {
        QuotaPlan p = getQuotaPlanById(id);
        p.setActive(false);
        repo.save(p);
    }
}
