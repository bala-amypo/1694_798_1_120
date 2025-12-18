package com.example.demo.service;

import com.example.demo.entity.ApiKey;

import java.util.List;

public interface ApiKeyService {
    List<ApiKey> getAllKeys();
    ApiKey getKeyById(Long id);
    ApiKey createKey(ApiKey key);
    ApiKey updateKey(Long id, ApiKey key);
    void deleteKey(Long id);
}
