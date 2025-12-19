package com.example.demo.service.impl;

import com.example.demo.entity.ApiKey;
import com.example.demo.entity.RateLimitEnforcement;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.RateLimitEnforcementRepository;
import com.example.demo.service.RateLimitEnforcementService;

import java.util.List;

public class RateLimitEnforcementServiceImpl implements RateLimitEnforcementService {

    private final RateLimitEnforcementRepository enforcementRepo;
    private final ApiKeyRepository apiKeyRepo;

    public RateLimitEnforcementServiceImpl(
            RateLimitEnforcementRepository enforcementRepo,
            ApiKeyRepository apiKeyRepo) {
        this.enforcementRepo = enforcementRepo;
        this.apiKeyRepo = apiKeyRepo;
    }

    @Override
    public RateLimitEnforcement createEnforcement(RateLimitEnforcement enforcement) {

        if (enforcement.getLimitExceededBy() < 1) {
            throw new BadRequestException("limitExceededBy must be >= 1");
        }

        ApiKey apiKey = apiKeyRepo.findById(enforcement.getApiKey().getId())
                .orElseThrow(() -> new ResourceNotFoundException("ApiKey not found"));

        enforcement.setApiKey(apiKey);
        return enforcementRepo.save(enforcement);
    }

    @Override
    public RateLimitEnforcement getEnforcementById(Long id) {
        return enforcementRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("RateLimitEnforcement not found"));
    }

    @Override
    public List<RateLimitEnforcement> getEnforcementsForKey(Long keyId) {
        return enforcementRepo.findByApiKey_Id(keyId);
    }
}
