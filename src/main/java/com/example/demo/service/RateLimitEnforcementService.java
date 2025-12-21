package com.example.demo.service;

import com.example.demo.dto.RateLimitEnforcementDto;
import java.util.List;

public interface RateLimitEnforcementService {
    RateLimitEnforcementDto createEnforcement(RateLimitEnforcementDto dto);
    List<RateLimitEnforcementDto> getEnforcementByApiKey(Long apiKeyId);
    void deleteEnforcement(Long id);
}
