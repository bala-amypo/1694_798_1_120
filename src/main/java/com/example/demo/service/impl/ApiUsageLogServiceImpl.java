package com.example.demo.service.impl;

import com.example.demo.dto.ApiUsageLogDto;
import com.example.demo.entity.ApiKey;
import com.example.demo.entity.ApiUsageLog;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.ApiUsageLogRepository;
import com.example.demo.service.ApiUsageLogService;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ApiUsageLogServiceImpl implements ApiUsageLogService {

    private final ApiUsageLogRepository repo;
    private final ApiKeyRepository keyRepo;

    public ApiUsageLogServiceImpl(ApiUsageLogRepository repo, ApiKeyRepository keyRepo) {
        this.repo = repo;
        this.keyRepo = keyRepo;
    }

    @Override
    public ApiUsageLogDto logUsage(ApiUsageLogDto dto) {
        ApiKey key = keyRepo.findById(dto.getApiKeyId())
                .orElseThrow(() -> new ResourceNotFoundException("Key not found"));

        Timestamp now = new Timestamp(System.currentTimeMillis());
        if (dto.getTimestamp().after(now)) {
            throw new BadRequestException("Timestamp cannot be future");
        }

        ApiUsageLog log = new ApiUsageLog();
        log.setApiKey(key);
        log.setEndpoint(dto.getEndpoint());
        log.setTimestamp(dto.getTimestamp());

        repo.save(log);
        dto.setId(log.getId());
        return dto;
    }

    @Override
    public List<ApiUsageLogDto> getUsageForApiKey(Long keyId) {
        return repo.findByApiKey_Id(keyId).stream().map(log -> {
            ApiUsageLogDto dto = new ApiUsageLogDto();
            dto.setId(log.getId());
            dto.setEndpoint(log.getEndpoint());
            dto.setTimestamp(log.getTimestamp());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<ApiUsageLogDto> getUsageForToday(Long keyId) {
        LocalDate today = LocalDate.now();
        LocalDateTime start = today.atStartOfDay();
        LocalDateTime end = today.atTime(23, 59, 59);

        return repo.findByApiKey_Id(keyId)
                .stream()
                .filter(log -> {
                    LocalDateTime ts = log.getTimestamp().toLocalDateTime();
                    return ts.isAfter(start) && ts.isBefore(end);
                })
                .map(log -> {
                    ApiUsageLogDto dto = new ApiUsageLogDto();
                    dto.setId(log.getId());
                    return dto;
                }).collect(Collectors.toList());
    }

    @Override
    public Long countRequestsToday(Long keyId) {
        return (long) getUsageForToday(keyId).size();
    }
}
