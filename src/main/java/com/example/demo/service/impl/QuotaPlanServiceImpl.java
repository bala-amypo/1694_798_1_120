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
        QuotaPlan p = new QuotaPlan();
        p.setPlanName(dto.getPlanName());
        p.setDailyLimit(dto.getDailyLimit());
        p.setDescription(dto.getDescription());
        p.setActive(true);
        return convert(repo.save(p));
    }

    @Override
    public QuotaPlanDto updateQuotaPlan(Long id, QuotaPlanDto dto) {
        QuotaPlan p = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Plan not found"));
        if (dto.getPlanName() != null) p.setPlanName(dto.getPlanName());
        if (dto.getDailyLimit() != null) p.setDailyLimit(dto.getDailyLimit());
        if (dto.getDescription() != null) p.setDescription(dto.getDescription());
        if (dto.getActive() != null) p.setActive(dto.getActive());
        return convert(repo.save(p));
    }

    @Override
    public QuotaPlanDto getQuotaPlanById(Long id) {
        return convert(repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Plan not found")));
    }

    @Override
    public List<QuotaPlanDto> getAllPlans() {
        return repo.findAll().stream().map(this::convert).collect(Collectors.toList());
    }

    @Override
    public void deactivateQuotaPlan(Long id) {
        QuotaPlan p = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Plan not found"));
        p.setActive(false);
        repo.save(p);
    }

    private QuotaPlanDto convert(QuotaPlan p) {
        QuotaPlanDto dto = new QuotaPlanDto();
        dto.setId(p.getId());
        dto.setPlanName(p.getPlanName());
        dto.setDailyLimit(p.getDailyLimit());
        dto.setActive(p.getActive());
        dto.setDescription(p.getDescription());
        return dto;
    }
}
