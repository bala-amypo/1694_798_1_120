package com.example.demo.service.impl;

import com.example.demo.entity.RateLimitEnforcement;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.RateLimitEnforcementRepository;
import com.example.demo.service.RateLimitEnforcementService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RateLimitEnforcementServiceImpl
        implements RateLimitEnforcementService {

    private final RateLimitEnforcementRepository repository;
    private final ApiKeyRepository apiKeyRepository;

    public RateLimitEnforcementServiceImpl(
            RateLimitEnforcementRepository repository,
            ApiKeyRepository apiKeyRepository) {
        this.repository = repository;
        this.apiKeyRepository = apiKeyRepository;
    }

    @Override
    public RateLimitEnforcement createEnforcement(
            RateLimitEnforcement enforcement) {

        if (enforcement.getLimitExceededBy() == null ||
                enforcement.getLimitExceededBy() < 1) {
            throw new BadRequestException("Limit exceeded must be >= 1");
        }
        return repository.save(enforcement);
    }

    @Override
    public List<RateLimitEnforcement> getEnforcementsForKey(Long keyId) {
        apiKeyRepository.findById(keyId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("API key not found"));
        return repository.findByApiKey_Id(keyId);
    }

    @Override
    public RateLimitEnforcement getEnforcementById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Enforcement not found"));
    }
}
