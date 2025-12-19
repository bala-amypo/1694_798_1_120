package com.example.demo.service.impl;

import com.example.demo.entity.ApiKey;
import com.example.demo.entity.KeyExemption;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.KeyExemptionRepository;
import com.example.demo.service.KeyExemptionService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class KeyExemptionServiceImpl implements KeyExemptionService {

    private final KeyExemptionRepository exemptionRepository;
    private final ApiKeyRepository apiKeyRepository;

    public KeyExemptionServiceImpl(KeyExemptionRepository exemptionRepository,
                                   ApiKeyRepository apiKeyRepository) {
        this.exemptionRepository = exemptionRepository;
        this.apiKeyRepository = apiKeyRepository;
    }

    @Override
    public KeyExemption createExemption(KeyExemption exemption) {

        // validation rules
        if (exemption.getTemporaryExtensionLimit() != null &&
                exemption.getTemporaryExtensionLimit() < 0) {
            throw new BadRequestException("Temporary extension limit must be >= 0");
        }

        if (exemption.getValidUntil() != null &&
                exemption.getValidUntil().isBefore(LocalDateTime.now())) {
            throw new BadRequestException("validUntil must be in the future");
        }

        ApiKey apiKey = apiKeyRepository.findById(exemption.getApiKey().getId())
                .orElseThrow(() -> new ResourceNotFoundException("ApiKey not found"));

        exemption.setApiKey(apiKey);

        return exemptionRepository.save(exemption);
    }

    @Override
    public KeyExemption updateExemption(Long id, KeyExemption exemption) {

        KeyExemption existing = exemptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("KeyExemption not found"));

        if (exemption.getTemporaryExtensionLimit() != null
                && exemption.getTemporaryExtensionLimit() < 0) {
            throw new BadRequestException("Temporary extension limit must be >= 0");
        }

        if (exemption.getValidUntil() != null
                && exemption.getValidUntil().isBefore(LocalDateTime.now())) {
            throw new BadRequestException("validUntil must be in the future");
        }

        existing.setNotes(exemption.getNotes());
        existing.setUnlimitedAccess(exemption.getUnlimitedAccess());
        existing.setTemporaryExtensionLimit(exemption.getTemporaryExtensionLimit());
        existing.setValidUntil(exemption.getValidUntil());

        return exemptionRepository.save(existing);
    }

    @Override
    public Optional<KeyExemption> getExemptionByKey(Long apiKeyId) {
        return exemptionRepository.findByApiKey_Id(apiKeyId);
    }

    @Override
    public List<KeyExemption> getAllExemptions() {
        return exemptionRepository.findAll();
    }
}
