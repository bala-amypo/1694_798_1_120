package com.example.demo.service.impl;

import com.example.demo.entity.KeyExemption;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.KeyExemptionRepository;
import com.example.demo.service.KeyExemptionService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class KeyExemptionServiceImpl implements KeyExemptionService {

    private final KeyExemptionRepository repo;
    private final ApiKeyRepository keyRepo;

    public KeyExemptionServiceImpl(KeyExemptionRepository repo,
                                   ApiKeyRepository keyRepo) {
        this.repo = repo;
        this.keyRepo = keyRepo;
    }

    @Override
    public KeyExemption createExemption(KeyExemption exemption) {
        if (exemption.getValidUntil().before(new Timestamp(System.currentTimeMillis()))) {
            throw new BadRequestException("validUntil must be in the future");
        }
        return repo.save(exemption);
    }

    @Override
    public KeyExemption updateExemption(Long id, KeyExemption exemption) {
        KeyExemption existing = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Exemption not found"));
        existing.setUnlimitedAccess(exemption.getUnlimitedAccess());
        existing.setTemporaryExtensionLimit(exemption.getTemporaryExtensionLimit());
        existing.setValidUntil(exemption.getValidUntil());
        return repo.save(existing);
    }

    @Override
    public KeyExemption getExemptionByKey(Long apiKeyId) {
        return repo.findByApiKey_Id(apiKeyId);
    }

    @Override
    public List<KeyExemption> getAllExemptions() {
        return repo.findAll();
    }
}
