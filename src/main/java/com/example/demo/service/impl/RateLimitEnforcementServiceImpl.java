package com.example.demo.service.impl;

import com.example.demo.dto.RateLimitEnforcementDto;
import com.example.demo.entity.ApiKey;
import com.example.demo.entity.RateLimitEnforcement;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.RateLimitEnforcementRepository;
import com.example.demo.service.RateLimitEnforcementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RateLimitEnforcementServiceImpl implements RateLimitEnforcementService {

    @Autowired
    private RateLimitEnforcementRepository repo;

    @Autowired
    private ApiKeyRepository apiKeyRepo;

    @Override
    public RateLimitEnforcementDto createEnforcement(RateLimitEnforcementDto dto) {
        ApiKey key = apiKeyRepo.findById(dto.getApiKeyId()).orElseThrow();
        RateLimitEnforcement e = new RateLimitEnforcement();
        e.setApiKey(key);
        e.setBlockedAt(dto.getBlockedAt());
        e.setLimitExceededBy(dto.getLimitExceededBy());
        e.setMessage(dto.getMessage());
        return convert(repo.save(e));
    }

    @Override
    public RateLimitEnforcementDto getEnforcementById(Long id) {
        return convert(repo.findById(id).orElseThrow());
    }

    @Override
    public List<RateLimitEnforcementDto> getEnforcementsForKey(Long apiKeyId) {
        return repo.findByApiKeyId(apiKeyId)
                .stream().map(this::convert).collect(Collectors.toList());
    }

    private RateLimitEnforcementDto convert(RateLimitEnforcement e) {
        RateLimitEnforcementDto dto = new RateLimitEnforcementDto();
        dto.setId(e.getId());
        dto.setApiKeyId(e.getApiKey().getId());
        dto.setBlockedAt(e.getBlockedAt());
        dto.setLimitExceededBy(e.getLimitExceededBy());
        dto.setMessage(e.getMessage());
        return dto;
    }
}
