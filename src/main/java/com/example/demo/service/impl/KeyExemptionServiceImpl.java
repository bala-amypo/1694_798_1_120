package com.example.demo.service.impl;

import com.example.demo.dto.KeyExemptionDto;
import com.example.demo.entity.ApiKey;
import com.example.demo.entity.KeyExemption;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.KeyExemptionRepository;
import com.example.demo.service.KeyExemptionService;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

public class KeyExemptionServiceImpl implements KeyExemptionService {

    private final KeyExemptionRepository repo;
    private final ApiKeyRepository keyRepo;

    public KeyExemptionServiceImpl(KeyExemptionRepository repo, ApiKeyRepository keyRepo) {
        this.repo = repo;
        this.keyRepo = keyRepo;
    }

    @Override
    public KeyExemptionDto createExemption(KeyExemptionDto dto) {
        ApiKey key = keyRepo.findById(dto.getApiKeyId())
                .orElseThrow(() -> new ResourceNotFoundException("Key not found"));

        if (dto.getTemporaryExtensionLimit() != null && dto.getTemporaryExtensionLimit() < 0) {
            throw new BadRequestException("Temporary limit must be >= 0");
        }

        Timestamp now = new Timestamp(System.currentTimeMillis());
        if (dto.getValidUntil().before(now)) {
            throw new BadRequestException("validUntil must be in future");
        }

        KeyExemption e = new KeyExemption();
        e.setApiKey(key);
        e.setNotes(dto.getNotes());
        e.setUnlimitedAccess(dto.getUnlimitedAccess());
        e.setTemporaryExtensionLimit(dto.getTemporaryExtensionLimit());
        e.setValidUntil(dto.getValidUntil());

        repo.save(e);
        dto.setId(e.getId());
        return dto;
    }

    @Override
    public KeyExemptionDto updateExemption(Long id, KeyExemptionDto dto) {
        KeyExemption e = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Exemption not found"));

        if (dto.getTemporaryExtensionLimit() < 0) {
            throw new BadRequestException("Temporary limit must be >= 0");
        }

        Timestamp now = new Timestamp(System.currentTimeMillis());
        if (dto.getValidUntil().before(now)) {
            throw new BadRequestException("validUntil must be future");
        }

        e.setNotes(dto.getNotes());
        e.setUnlimitedAccess(dto.getUnlimitedAccess());
        e.setTemporaryExtensionLimit(dto.getTemporaryExtensionLimit());
        e.setValidUntil(dto.getValidUntil());

        repo.save(e);
        return dto;
    }

    @Override
    public KeyExemptionDto getExemptionByKey(Long apiKeyId) {
        KeyExemption e = repo.findByApiKey_Id(apiKeyId)
                .orElseThrow(() -> new ResourceNotFoundException("Exemption not found"));

        KeyExemptionDto dto = new KeyExemptionDto();
        dto.setId(e.getId());
        dto.setNotes(e.getNotes());
        dto.setUnlimitedAccess(e.getUnlimitedAccess());
        return dto;
    }

    @Override
    public List<KeyExemptionDto> getAllExemptions() {
        return repo.findAll().stream().map(e -> {
            KeyExemptionDto dto = new KeyExemptionDto();
            dto.setId(e.getId());
            return dto;
        }).collect(Collectors.toList());
    }
}
