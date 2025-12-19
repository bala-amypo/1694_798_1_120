package com.example.demo.service;

import com.example.demo.entity.ApiKey;

import java.util.List;
import java.util.Optional;

public interface ApiKeyService {

    ApiKey createApiKey(ApiKey apiKey);

    ApiKey updateApiKey(Long id, ApiKey apiKey);

    ApiKey getApiKeyById(Long id);

    Optional<ApiKey> getApiKeyByValue(String keyValue);

    List<ApiKey> getAllApiKeys();

    void deactivateApiKey(Long id);
}
