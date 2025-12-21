package com.example.demo.service.impl;

import com.example.demo.dto.QuotaPlanDto;
import com.example.demo.entity.QuotaPlan;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.QuotaPlanRepository;
import com.example.demo.service.QuotaPlanService;

import java.util.List;
import java.util.stream.Collectors;

public class QuotaPlanServiceImpl implements QuotaPlanService {

    private final QuotaPlanRepository repo;

    public QuotaPlanServiceImpl(QuotaPlanRepository repo) {
        this.repo = repo;
    }

    @Override
    public QuotaPlanDto createQuotaPlan(QuotaPlanDto dto) {
        if (dto.getDailyLimit() <= 0) {
            throw new BadRequestException("Daily limit must be > 0");
        }

        QuotaPlan plan = new QuotaPlan();
        plan.setPlanName(dto.getPlanName());
        plan.setDailyLimit(dto.getDailyLimit());
        plan.setDescription(dto.getDescription());
        plan.setActive(true);

        QuotaPlan saved = repo.save(plan);
        dto.setId(saved.getId());
        return dto;
    }

    @Override
    public QuotaPlanDto updateQuotaPlan(Long id, QuotaPlanDto dto) {
        QuotaPlan plan = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plan not found"));

        if (dto.getDailyLimit() <= 0) {
            throw new BadRequestException("Daily limit must be > 0");
        }

        plan.setDailyLimit(dto.getDailyLimit());
        plan.setDescription(dto.getDescription());
        plan.setActive(dto.getActive());

        repo.save(plan);
        return dto;
    }

    @Override
    public QuotaPlanDto getQuotaPlanById(Long id) {
        QuotaPlan plan = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plan not found"));

        QuotaPlanDto dto = new QuotaPlanDto();
        dto.setId(plan.getId());
        dto.setPlanName(plan.getPlanName());
        dto.setDailyLimit(plan.getDailyLimit());
        dto.setActive(plan.getActive());
        dto.setDescription(plan.getDescription());
        return dto;
    }

    @Override
    public List<QuotaPlanDto> getAllPlans() {
        return repo.findAll().stream().map(plan -> {
            QuotaPlanDto dto = new QuotaPlanDto();
            dto.setId(plan.getId());
            dto.setPlanName(plan.getPlanName());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public void deactivateQuotaPlan(Long id) {
        QuotaPlan plan = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plan not found"));
        plan.setActive(false);
        repo.save(plan);
    }
}
