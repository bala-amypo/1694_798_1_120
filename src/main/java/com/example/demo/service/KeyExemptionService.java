package com.example.demo.service;

import com.example.demo.dto.KeyExemptionDto;

public interface KeyExemptionService {
    KeyExemptionDto createExemption(KeyExemptionDto dto);
    KeyExemptionDto getExemptionByApiKey(Long apiKeyId);
    void deleteExemption(Long id);
}
