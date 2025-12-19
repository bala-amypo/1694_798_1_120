package com.example.demo.service.impl;

import com.example.demo.entity.ApiKey;
import com.example.demo.entity.QuotaPlan;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.QuotaPlanRepository;
import com.example.demo.service.ApiKeyService;

import java.util.List;
import java.util.Optional;

public class ApiKeyServiceImpl implements ApiKeyService {

    private final ApiKeyRepository apiKeyRepo;
    private final QuotaPlanRepository planRepo;

    public ApiKeyServiceImpl(ApiKeyRepository apiKeyRepo,
                             QuotaPlanRepository planRepo) {
        this.apiKeyRepo = apiKeyRepo;
        this.planRepo = planRepo;
    }

    @Override
    public ApiKey createApiKey(ApiKey apiKey) {

        QuotaPlan plan = planRepo.findById(apiKey.getPlan().getId())
                .orElseThrow(() -> new ResourceNotFoundException("QuotaPlan not found"));

        if (!plan.getActive()) {
            throw new BadRequestException("Plan is inactive");
        }

        if (apiKeyRepo.findByKeyValue(apiKey.getKeyValue()).isPresent()) {
            throw new IllegalArgumentException("Duplicate API key");
        }

        return apiKeyRepo.save(apiKey);
    }

    @Override
    public ApiKey updateApiKey(Long id, ApiKey apiKey) {
        ApiKey existing = getApiKeyById(id);

        existing.setKeyValue(apiKey.getKeyValue());
        existing.setOwnerId(apiKey.getOwnerId());
        existing.setPlan(apiKey.getPlan());
        existing.setActive(apiKey.getActive());

        return apiKeyRepo.save(existing);
    }

    @Override
    public ApiKey getApiKeyById(Long id) {
        return apiKeyRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ApiKey not found"));
    }

    @Override
    public Optional<ApiKey> getApiKeyByValue(String keyValue) {
        return apiKeyRepo.findByKeyValue(keyValue);
    }

    @Override
    public List<ApiKey> getAllApiKeys() {
        return apiKeyRepo.findAll();
    }

    @Override
    public void deactivateApiKey(Long id) {
        ApiKey key = getApiKeyById(id);
        key.setActive(false);
        apiKeyRepo.save(key);
    }
}
