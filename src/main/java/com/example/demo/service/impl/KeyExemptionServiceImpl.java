package com.example.demo.service.impl;

import com.example.demo.dto.KeyExemptionDto;
import com.example.demo.entity.ApiKey;
import com.example.demo.entity.KeyExemption;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.KeyExemptionRepository;
import com.example.demo.service.KeyExemptionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KeyExemptionServiceImpl implements KeyExemptionService {

    @Autowired
    private KeyExemptionRepository repo;

    @Autowired
    private ApiKeyRepository keyRepo;

    @Override
    public KeyExemptionDto createExemption(KeyExemptionDto dto) {
        ApiKey key = keyRepo.findById(dto.getApiKeyId()).orElseThrow();
        KeyExemption e = new KeyExemption();
        e.setApiKey(key);
        e.setNotes(dto.getNotes());
        e.setUnlimitedAccess(dto.getUnlimitedAccess());
        e.setTemporaryExtensionLimit(dto.getTemporaryExtensionLimit());
        e.setValidUntil(dto.getValidUntil());
        return convert(repo.save(e));
    }

    @Override
    public KeyExemptionDto getExemptionByApiKey(Long apiKeyId) {
        KeyExemption e = repo.findByApiKeyId(apiKeyId).orElse(null);
        if (e == null) return null;
        return convert(e);
    }

    @Override
    public void deleteExemption(Long id) {
        repo.delete(repo.findById(id).orElseThrow());
    }

    private KeyExemptionDto convert(KeyExemption e) {
        KeyExemptionDto dto = new KeyExemptionDto();
        dto.setId(e.getId());
        dto.setApiKeyId(e.getApiKey().getId());
        dto.setNotes(e.getNotes());
        dto.setUnlimitedAccess(e.getUnlimitedAccess());
        dto.setTemporaryExtensionLimit(e.getTemporaryExtensionLimit());
        dto.setValidUntil(e.getValidUntil());
        return dto;
    }
}
