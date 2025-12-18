package com.example.demo.service.impl;

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

    private final ApiUsageLogRepository repository;
    private final ApiKeyRepository apiKeyRepository;

    public ApiUsageLogServiceImpl(ApiUsageLogRepository repository,
                                  ApiKeyRepository apiKeyRepository) {
        this.repository = repository;
        this.apiKeyRepository = apiKeyRepository;
    }

    @Override
    public ApiUsageLog logUsage(ApiUsageLog log) {
        if (log.getTimestamp().isAfter(Instant.now())) {
            throw new BadRequestException("Timestamp cannot be in the future");
        }
        return repository.save(log);
    }

    @Override
    public List<ApiUsageLog> getUsageForApiKey(Long keyId) {
        apiKeyRepository.findById(keyId)
                .orElseThrow(() -> new ResourceNotFoundException("API key not found"));
        return repository.findByApiKey_Id(keyId);
    }

    @Override
    public List<ApiUsageLog> getUsageForToday(Long keyId) {
        Instant start = LocalDate.now()
                .atStartOfDay(ZoneId.systemDefault()).toInstant();
        Instant end = Instant.now();
        return repository.findForKeyBetween(keyId, start, end);
    }

    @Override
    public long countRequestsToday(Long keyId) {
        Instant start = LocalDate.now()
                .atStartOfDay(ZoneId.systemDefault()).toInstant();
        Instant end = Instant.now();
        return repository.countForKeyBetween(keyId, start, end);
    }
}
