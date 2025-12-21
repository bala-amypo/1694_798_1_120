package com.example.demo.service.impl;

import com.example.demo.dto.ApiKeyDto;
import com.example.demo.entity.ApiKey;
import com.example.demo.entity.QuotaPlan;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.QuotaPlanRepository;
import com.example.demo.service.ApiKeyService;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

public class ApiKeyServiceImpl implements ApiKeyService {

    private final ApiKeyRepository repo;
    private final QuotaPlanRepository planRepo;

    public ApiKeyServiceImpl(ApiKeyRepository repo, QuotaPlanRepository planRepo) {
        this.repo = repo;
        this.planRepo = planRepo;
    }

    @Override
    public ApiKeyDto createApiKey(ApiKeyDto dto) {
        QuotaPlan plan = planRepo.findById(dto.getPlanId())
                .orElseThrow(() -> new ResourceNotFoundException("Plan not found"));

        ApiKey key = new ApiKey();
        key.setKeyValue(dto.getKeyValue());
        key.setOwnerId(dto.getOwnerId());
        key.setPlan(plan);
        key.setActive(true);
        key.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        key.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        ApiKey saved = repo.save(key);

        dto.setId(saved.getId());
        return dto;
    }

    @Override
    public ApiKeyDto updateApiKey(Long id, ApiKeyDto dto) {
        ApiKey key = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Key not found"));

        key.setActive(dto.getActive());
        key.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        repo.save(key);
        return dto;
    }

    @Override
    public ApiKeyDto getApiKeyById(Long id) {
        ApiKey key = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Key not found"));

        ApiKeyDto dto = new ApiKeyDto();
        dto.setId(key.getId());
        dto.setKeyValue(key.getKeyValue());
        dto.setOwnerId(key.getOwnerId());
        dto.setPlanId(key.getPlan().getId());
        dto.setActive(key.getActive());
        return dto;
    }

    @Override
    public ApiKeyDto getApiKeyByValue(String keyValue) {
        ApiKey key = repo.findByKeyValue(keyValue)
                .orElseThrow(() -> new ResourceNotFoundException("Key not found"));

        ApiKeyDto dto = new ApiKeyDto();
        dto.setId(key.getId());
        return dto;
    }

    @Override
    public List<ApiKeyDto> getAllApiKeys() {
        return repo.findAll().stream().map(k -> {
            ApiKeyDto dto = new ApiKeyDto();
            dto.setId(k.getId());
            dto.setKeyValue(k.getKeyValue());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public void deactivateApiKey(Long id) {
        ApiKey key = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Key not found"));
        key.setActive(false);
        repo.save(key);
    }
}
