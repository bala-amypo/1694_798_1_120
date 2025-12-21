package com.example.demo.service.impl;

import com.example.demo.dto.RateLimitEnforcementDto;
import com.example.demo.entity.ApiKey;
import com.example.demo.entity.RateLimitEnforcement;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.RateLimitEnforcementRepository;
import com.example.demo.service.RateLimitEnforcementService;

import java.util.List;
import java.util.stream.Collectors;

public class RateLimitEnforcementServiceImpl implements RateLimitEnforcementService {

    private final RateLimitEnforcementRepository repo;
    private final ApiKeyRepository keyRepo;

    public RateLimitEnforcementServiceImpl(RateLimitEnforcementRepository repo, ApiKeyRepository keyRepo) {
        this.repo = repo;
        this.keyRepo = keyRepo;
    }

    @Override
    public RateLimitEnforcementDto createEnforcement(RateLimitEnforcementDto dto) {
        if (dto.getLimitExceededBy() < 1) {
            throw new BadRequestException("limitExceededBy must be >= 1");
        }

        ApiKey key = keyRepo.findById(dto.getApiKeyId())
                .orElseThrow(() -> new ResourceNotFoundException("Key not found"));

        RateLimitEnforcement e = new RateLimitEnforcement();
        e.setApiKey(key);
        e.setBlockedAt(dto.getBlockedAt());
        e.setLimitExceededBy(dto.getLimitExceededBy());
        e.setMessage(dto.getMessage());

        repo.save(e);
        dto.setId(e.getId());
        return dto;
    }

    @Override
    public RateLimitEnforcementDto getEnforcementById(Long id) {
        RateLimitEnforcement e = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enforcement not found"));

        RateLimitEnforcementDto dto = new RateLimitEnforcementDto();
        dto.setId(e.getId());
        dto.setMessage(e.getMessage());
        dto.setLimitExceededBy(e.getLimitExceededBy());
        return dto;
    }

    @Override
    public List<RateLimitEnforcementDto> getEnforcementsForKey(Long keyId) {
        return repo.findByApiKey_Id(keyId)
                .stream()
                .map(e -> {
                    RateLimitEnforcementDto dto = new RateLimitEnforcementDto();
                    dto.setId(e.getId());
                    return dto;
                }).collect(Collectors.toList());
    }
}
