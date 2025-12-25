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

    private final ApiUsageLogRepository repo;
    private final ApiKeyRepository keyRepo;

    public ApiUsageLogServiceImpl(ApiUsageLogRepository repo, ApiKeyRepository keyRepo) {
        this.repo = repo;
        this.keyRepo = keyRepo;
    }

    @Override
    public ApiUsageLog logUsage(ApiUsageLog log) {
        if (log.getTimestamp().isAfter(Instant.now())) {
            throw new BadRequestException("Future timestamp");
        }

        keyRepo.findById(log.getApiKey().getId())
                .orElseThrow(() -> new ResourceNotFoundException("ApiKey not found"));

        return repo.save(log);
    }

    @Override
    public List<ApiUsageLog> getUsageForApiKey(Long keyId) {
        return repo.findByApiKey_Id(keyId);
    }

    @Override
    public List<ApiUsageLog> getUsageForToday(Long keyId) {
        Instant start = LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant();
        Instant end = start.plus(Duration.ofDays(1));
        return repo.findForKeyBetween(keyId, start, end);
    }

    @Override
    public int countRequestsToday(Long keyId) {
        Instant start = LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant();
        Instant end = start.plus(Duration.ofDays(1));
        return repo.countForKeyBetween(keyId, start, end);
    }
}
