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

    private final KeyExemptionRepository repository;
    private final ApiKeyRepository apiKeyRepository;

    public KeyExemptionServiceImpl(KeyExemptionRepository repository,
                                   ApiKeyRepository apiKeyRepository) {
        this.repository = repository;
        this.apiKeyRepository = apiKeyRepository;
    }

    @Override
    public KeyExemption createExemption(KeyExemption exemption) {
        if (exemption.getValidUntil() != null &&
                exemption.getValidUntil().isBefore(Instant.now())) {
            throw new BadRequestException("validUntil must be in the future");
        }
        return repository.save(exemption);
    }

    @Override
    public KeyExemption updateExemption(Long id, KeyExemption exemption) {
        KeyExemption existing = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Exemption not found"));

        existing.setNotes(exemption.getNotes());
        existing.setUnlimitedAccess(exemption.getUnlimitedAccess());
        existing.setTemporaryExtensionLimit(
                exemption.getTemporaryExtensionLimit());
        existing.setValidUntil(exemption.getValidUntil());

        return repository.save(existing);
    }

    @Override
    public KeyExemption getExemptionByKey(Long apiKeyId) {
        apiKeyRepository.findById(apiKeyId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("API key not found"));

        return repository.findByApiKey_Id(apiKeyId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Exemption not found"));
    }

    @Override
    public List<KeyExemption> getAllExemptions() {
        return repository.findAll();
    }
}
