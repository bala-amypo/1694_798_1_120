package com.example.demo.service.impl;

import com.example.demo.dto.KeyExemptionDto;
import com.example.demo.entity.ApiKey;
import com.example.demo.entity.KeyExemption;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.KeyExemptionRepository;
import com.example.demo.service.KeyExemptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KeyExemptionServiceImpl implements KeyExemptionService {

    @Autowired
    private KeyExemptionRepository repo;

    @Autowired
    private ApiKeyRepository apiKeyRepo;

    @Override
    public KeyExemptionDto createExemption(KeyExemptionDto dto) {
        ApiKey key = apiKeyRepo.findById(dto.getApiKeyId()).orElseThrow();
        KeyExemption e = new KeyExemption();
        e.setApiKey(key);
        e.setUnlimitedAccess(dto.getUnlimitedAccess());
        e.setTemporaryExtensionLimit(dto.getTemporaryExtensionLimit());
        e.setValidUntil(dto.getValidUntil());
        e.setNotes(dto.getNotes());
        return convert(repo.save(e));
    }

    @Override
    public KeyExemptionDto updateExemption(Long id, KeyExemptionDto dto) {
        KeyExemption e = repo.findById(id).orElseThrow();
        if (dto.getUnlimitedAccess() != null) e.setUnlimitedAccess(dto.getUnlimitedAccess());
        if (dto.getTemporaryExtensionLimit() != null) e.setTemporaryExtensionLimit(dto.getTemporaryExtensionLimit());
        if (dto.getValidUntil() != null) e.setValidUntil(dto.getValidUntil());
        if (dto.getNotes() != null) e.setNotes(dto.getNotes());
        return convert(repo.save(e));
    }

    @Override
    public KeyExemptionDto getExemptionByKey(Long apiKeyId) {
        KeyExemption e = repo.findByApiKeyId(apiKeyId);
        return e == null ? null : convert(e);
    }

    @Override
    public List<KeyExemptionDto> getAllExemptions() {
        return repo.findAll()
                .stream().map(this::convert)
                .collect(Collectors.toList());
    }

    private KeyExemptionDto convert(KeyExemption e) {
        KeyExemptionDto dto = new KeyExemptionDto();
        dto.setId(e.getId());
        dto.setApiKeyId(e.getApiKey().getId());
        dto.setUnlimitedAccess(e.getUnlimitedAccess());
        dto.setTemporaryExtensionLimit(e.getTemporaryExtensionLimit());
        dto.setValidUntil(e.getValidUntil());
        dto.setNotes(e.getNotes());
        return dto;
    }
}
