package com.example.demo.service.impl;

import com.example.demo.entity.ApiKey;
import com.example.demo.entity.ApiUsageLog;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.ApiUsageLogRepository;
import com.example.demo.service.ApiUsageLogService;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.List;

@Service
public class ApiUsageLogServiceImpl implements ApiUsageLogService {

    private final ApiUsageLogRepository usageLogRepository;
    private final ApiKeyRepository apiKeyRepository;

    // ✅ Constructor Injection
    public ApiUsageLogServiceImpl(ApiUsageLogRepository usageLogRepository,
                                  ApiKeyRepository apiKeyRepository) {
        this.usageLogRepository = usageLogRepository;
        this.apiKeyRepository = apiKeyRepository;
    }

    @Override
    public ApiUsageLog logUsage(ApiUsageLog log) {

        // Validate timestamp
        if (log.getTimestamp().isAfter(LocalDateTime.now())) {
            throw new BadRequestException("Timestamp cannot be in the future");
        }

        // Validate API Key exists
        ApiKey apiKey = apiKeyRepository.findById(log.getApiKey().getId())
                .orElseThrow(() -> new ResourceNotFoundException("ApiKey not found"));

        log.setApiKey(apiKey);
        return usageLogRepository.save(log);
    }

    @Override
    public List<ApiUsageLog> getUsageForApiKey(Long keyId) {
        return usageLogRepository.findByApiKey_Id(keyId);
    }

    @Override
    public List<ApiUsageLog> getUsageForToday(Long keyId) {

        ZoneId zone = ZoneId.systemDefault();
        Instant start = LocalDate.now().atStartOfDay(zone).toInstant();
        Instant end = LocalDate.now().atTime(LocalTime.MAX).atZone(zone).toInstant();

        return usageLogRepository.findForKeyBetween(keyId, start, end);
    }

    @Override
    public int countRequestsToday(Long keyId) {

        ZoneId zone = ZoneId.systemDefault();
        Instant start = LocalDate.now().atStartOfDay(zone).toInstant();
        Instant end = LocalDate.now().atTime(LocalTime.MAX).atZone(zone).toInstant();

        return usageLogRepository.countForKeyBetween(keyId, start, end);
    }
}
