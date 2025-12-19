package com.example.demo.service.impl;

import com.example.demo.entity.ApiKey;
import com.example.demo.entity.KeyExemption;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.KeyExemptionRepository;
import com.example.demo.service.KeyExemptionService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class KeyExemptionServiceImpl implements KeyExemptionService {

    private final KeyExemptionRepository exemptionRepo;
    private final ApiKeyRepository apiKeyRepo;

    public KeyExemptionServiceImpl(KeyExemptionRepository exemptionRepo,
                                   ApiKeyRepository apiKeyRepo) {
        this.exemptionRepo = exemptionRepo;
        this.apiKeyRepo = apiKeyRepo;
    }

    @Override
    public KeyExemption createExemption(KeyExemption exemption) {

        if (exemption.getTemporaryExtensionLimit() != null &&
                exemption.getTemporaryExtensionLimit() < 0) {
            throw new BadRequestException("Invalid temporary extension limit");
        }

        if (exemption.getValidUntil() != null &&
                exemption.getValidUntil().isBefore(LocalDateTime.now())) {
            throw new BadRequestException("Invalid exemption expiry");
        }

        ApiKey apiKey = apiKeyRepo.findById(exemption.getApiKey().getId())
                .orElseThrow(() -> new ResourceNotFoundException("ApiKey not found"));

        exemption.setApiKey(apiKey);
        return exemptionRepo.save(exemption);
    }

    @Override
    public KeyExemption updateExemption(Long id, KeyExemption exemption) {

        KeyExemption existing = exemptionRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("KeyExemption not found"));

        if (exemption.getTemporaryExtensionLimit() != null &&
                exemption.getTemporaryExtensionLimit() < 0) {
            throw new BadRequestException("Invalid temporary extension limit");
        }

        if (exemption.getValidUntil() != null &&
                exemption.getValidUntil().isBefore(LocalDateTime.now())) {
            throw new BadRequestException("Invalid exemption expiry");
        }

        existing.setNotes(exemption.getNotes());
        existing.setUnlimitedAccess(exemption.getUnlimitedAccess());
        existing.setTemporaryExtensionLimit(exemption.getTemporaryExtensionLimit());
        existing.setValidUntil(exemption.getValidUntil());

        return exemptionRepo.save(existing);
    }

    @Override
    public Optional<KeyExemption> getExemptionByKey(Long apiKeyId) {
        return exemptionRepo.findByApiKey_Id(apiKeyId);
    }

    @Override
    public List<KeyExemption> getAllExemptions() {
        return exemptionRepo.findAll();
    }
}
