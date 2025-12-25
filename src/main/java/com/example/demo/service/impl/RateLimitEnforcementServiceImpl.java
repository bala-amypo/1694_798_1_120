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
public class RateLimitEnforcementServiceImpl implements RateLimitEnforcementService {

    private final RateLimitEnforcementRepository repo;
    private final ApiKeyRepository keyRepo;

    public RateLimitEnforcementServiceImpl(RateLimitEnforcementRepository repo,
                                          ApiKeyRepository keyRepo) {
        this.repo = repo;
        this.keyRepo = keyRepo;
    }

    @Override
    public RateLimitEnforcement createEnforcement(RateLimitEnforcement e) {
        if (e.getLimitExceededBy() <= 0) {
            throw new BadRequestException("Invalid limit");
        }

        keyRepo.findById(e.getApiKey().getId())
                .orElseThrow(() -> new ResourceNotFoundException("ApiKey not found"));

        return repo.save(e);
    }

    @Override
    public RateLimitEnforcement getEnforcementById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enforcement not found"));
    }

    @Override
    public List<RateLimitEnforcement> getEnforcementsForKey(Long keyId) {
        return repo.findByApiKey_Id(keyId);
    }
}
