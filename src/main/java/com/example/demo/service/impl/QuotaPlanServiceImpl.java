package com.example.demo.service.impl;

import com.example.demo.dto.QuotaPlanDto;
import com.example.demo.entity.QuotaPlan;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.QuotaPlanRepository;
import com.example.demo.service.QuotaPlanService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuotaPlanServiceImpl implements QuotaPlanService {

    @Autowired
    private QuotaPlanRepository repo;

    @Override
    public QuotaPlanDto createQuotaPlan(QuotaPlanDto dto) {
        QuotaPlan plan = new QuotaPlan();
        plan.setPlanName(dto.getPlanName());
        plan.setDailyLimit(dto.getDailyLimit());
        plan.setDescription(dto.getDescription());
        plan.setActive(true);
        QuotaPlan saved = repo.save(plan);
        return convert(saved);
    }

    @Override
    public QuotaPlanDto updateQuotaPlan(Long id, QuotaPlanDto dto) {
        QuotaPlan plan = repo.findById(id).orElseThrow();
        if (dto.getPlanName() != null) plan.setPlanName(dto.getPlanName());
        if (dto.getDailyLimit() != null) plan.setDailyLimit(dto.getDailyLimit());
        if (dto.getDescription() != null) plan.setDescription(dto.getDescription());
        if (dto.getActive() != null) plan.setActive(dto.getActive());
        return convert(repo.save(plan));
    }

    @Override
    public QuotaPlanDto getQuotaPlanById(Long id) {
        return convert(repo.findById(id).orElseThrow());
    }

    @Override
    public List<QuotaPlanDto> getAllQuotaPlans() {
        return repo.findAll().stream().map(this::convert).collect(Collectors.toList());
    }

    @Override
    public void deleteQuotaPlan(Long id) {
        repo.delete(repo.findById(id).orElseThrow());
    }

    private QuotaPlanDto convert(QuotaPlan plan) {
        QuotaPlanDto dto = new QuotaPlanDto();
        dto.setId(plan.getId());
        dto.setPlanName(plan.getPlanName());
        dto.setDailyLimit(plan.getDailyLimit());
        dto.setDescription(plan.getDescription());
        dto.setActive(plan.getActive());
        return dto;
    }
}
