package com.example.demo.service;

import com.example.demo.entity.RateLimitEnforcement;
import java.util.List;

public interface RateLimitEnforcementService {
    RateLimitEnforcement createEnforcement(RateLimitEnforcement e);
    RateLimitEnforcement getEnforcementById(Long id);
    List<RateLimitEnforcement> getEnforcementsForKey(Long keyId);
}
