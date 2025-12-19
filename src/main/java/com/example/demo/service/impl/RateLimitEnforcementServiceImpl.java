package com.example.demo.service.impl;

import com.example.demo.entity.ApiKey;
import com.example.demo.entity.RateLimitEnforcement;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.RateLimitEnforcementRepository;
import com.example.demo.service.RateLimitEnforcementService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RateLimitEnforcementServiceImpl implements RateLimitEnforcementService {

    private final RateLimitEnforcementRepository enforcementRepository;
    private final ApiKeyRepository apiKeyRepository;

    public RateLimitEnforcementServiceImpl(
            RateLimitEnforcementRepository enforcementRepository,
            ApiKeyRepository apiKeyRepository
    ) {
        this.enforcementRepository = enforcementRepository;
        this.apiKeyRepository = apiKeyRepository;
    }

    @Override
    public RateLimitEnforcement createEnforcement(RateLimitEnforcement enforcement) {

        if (enforcement.getLimitExceededBy() == null || enforcement.getLimitExceededBy() < 1) {
            throw new BadRequestException("limitExceededBy must be >= 1");
        }

        ApiKey apiKey = apiKeyRepository.findById(enforcement.getApiKey().getId())
                .orElseThrow(() -> new ResourceNotFoundException("ApiKey not found"));

        enforcement.setApiKey(apiKey);

        return enforcementRepository.save(enforcement);
    }

    @Override
    public RateLimitEnforcement getEnforcementById(Long id) {
        return enforcementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enforcement not found"));
    }

    @Override
    public List<RateLimitEnforcement> getEnforcementsForKey(Long keyId) {
        return enforcementRepository.findByApiKey_Id(keyId);
    }
}
