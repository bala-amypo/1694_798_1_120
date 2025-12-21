package com.example.demo.service.impl;

import com.example.demo.dto.KeyExemptionDto;
import com.example.demo.entity.ApiKey;
import com.example.demo.entity.KeyExemption;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.KeyExemptionRepository;
import com.example.demo.service.KeyExemptionService;
import org.springframework.stereotype.Service;

@Service
public class KeyExemptionServiceImpl implements KeyExemptionService {

    private final KeyExemptionRepository repo;
    private final ApiKeyRepository apiKeyRepo;

    public KeyExemptionServiceImpl(KeyExemptionRepository repo, ApiKeyRepository apiKeyRepo) {
        this.repo = repo;
        this.apiKeyRepo = apiKeyRepo;
    }

    @Override
    public KeyExemptionDto createExemption(KeyExemptionDto dto) {
        ApiKey key = apiKeyRepo.findById(dto.getApiKeyId()).orElseThrow();

        KeyExemption exemption = new KeyExemption();
        exemption.setApiKey(key);
        exemption.setTemporaryExtensionLimit(dto.getTemporaryExtensionLimit());
        exemption.setValidUntil(dto.getValidUntil());

        return convert(repo.save(exemption));
    }

    @Override
    public KeyExemptionDto updateExemption(Long id, KeyExemptionDto dto) {
        KeyExemption exemption = repo.findById(id).orElseThrow();

        exemption.setTemporaryExtensionLimit(dto.getTemporaryExtensionLimit());
        exemption.setValidUntil(dto.getValidUntil());

        return convert(repo.save(exemption));
    }

    @Override
    public KeyExemptionDto getExemptionByKey(Long apiKeyId) {
        KeyExemption exemption = repo.findByApiKeyId(apiKeyId);
        if (exemption == null) return null;
        return convert(exemption);
    }

    @Override
    public java.util.List<KeyExemptionDto> getAllExemptions() {
        return repo.findAll().stream()
                .map(this::convert)
                .toList();
    }

    private KeyExemptionDto convert(KeyExemption ex) {
        return new KeyExemptionDto(
                ex.getId(),
                ex.getApiKey().getId(),
                ex.getTemporaryExtensionLimit(),
                ex.getValidUntil()
        );
    }
}
