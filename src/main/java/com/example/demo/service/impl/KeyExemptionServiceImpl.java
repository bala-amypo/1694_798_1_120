package com.example.demo.service.impl;

import com.example.demo.entity.KeyExemption;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.KeyExemptionRepository;
import com.example.demo.service.KeyExemptionService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
@Service
public class KeyExemptionServiceImpl implements KeyExemptionService {

    private final KeyExemptionRepository repo;
    private final ApiKeyRepository keyRepo;

    public KeyExemptionServiceImpl(KeyExemptionRepository repo, ApiKeyRepository keyRepo) {
        this.repo = repo;
        this.keyRepo = keyRepo;
    }

    @Override
    public KeyExemption createExemption(KeyExemption e) {
        if (e.getTemporaryExtensionLimit() < 0) {
            throw new BadRequestException("Invalid extension limit");
        }
        if (e.getValidUntil() != null && e.getValidUntil().isBefore(Instant.now())) {
            throw new BadRequestException("Invalid expiry");
        }

        keyRepo.findById(e.getApiKey().getId())
                .orElseThrow(() -> new ResourceNotFoundException("ApiKey not found"));

        return repo.save(e);
    }

    @Override
    public KeyExemption updateExemption(Long id, KeyExemption e) {
        KeyExemption existing = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Exemption not found"));

        existing.setTemporaryExtensionLimit(e.getTemporaryExtensionLimit());
        existing.setValidUntil(e.getValidUntil());

        return repo.save(existing);
    }

    @Override
    public KeyExemption getExemptionByKey(Long apiKeyId) {
        return repo.findByApiKey_Id(apiKeyId)
                .orElseThrow(() -> new ResourceNotFoundException("Exemption not found"));
    }

    @Override
    public List<KeyExemption> getAllExemptions() {
        return repo.findAll();
    }
}