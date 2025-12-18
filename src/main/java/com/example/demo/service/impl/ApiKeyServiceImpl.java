package com.example.demo.service.impl;

import com.example.demo.entity.ApiKey;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.service.ApiKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApiKeyServiceImpl implements ApiKeyService {

    @Autowired
    private ApiKeyRepository apiKeyRepository;

    @Override
    public List<ApiKey> getAllKeys() {
        return apiKeyRepository.findAll();
    }

    @Override
    public ApiKey getKeyById(Long id) {
        return apiKeyRepository.findById(id).orElse(null);
    }

    @Override
    public ApiKey createKey(ApiKey key) {
        return apiKeyRepository.save(key);
    }

    @Override
    public ApiKey updateKey(Long id, ApiKey key) {
        Optional<ApiKey> existing = apiKeyRepository.findById(id);
        if (existing.isPresent()) {
            ApiKey k = existing.get();
            k.setKeyValue(key.getKeyValue());
            k.setPlan(key.getPlan());
            k.setActive(key.isActive());
            return apiKeyRepository.save(k);
        }
        return null;
    }

    @Override
    public void deleteKey(Long id) {
        apiKeyRepository.deleteById(id);
    }
}
