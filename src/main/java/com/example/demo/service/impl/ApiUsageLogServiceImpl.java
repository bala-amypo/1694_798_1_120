package com.example.demo.service.impl;

import com.example.demo.dto.ApiUsageLogDto;
import com.example.demo.entity.ApiKey;
import com.example.demo.entity.ApiUsageLog;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.ApiUsageLogRepository;
import com.example.demo.service.ApiUsageLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApiUsageLogServiceImpl implements ApiUsageLogService {

    @Autowired
    private ApiUsageLogRepository logRepo;

    @Autowired
    private ApiKeyRepository apiKeyRepo;

    @Override
    public ApiUsageLogDto logUsage(ApiUsageLogDto dto) {
        ApiKey key = apiKeyRepo.findById(dto.getApiKeyId()).orElseThrow();

        ApiUsageLog log = new ApiUsageLog();
        log.setApiKey(key);
        log.setEndpoint(dto.getEndpoint());
        log.setTimestamp(new Timestamp(System.currentTimeMillis()));

        return convert(logRepo.save(log));
    }

    @Override
    public List<ApiUsageLogDto> getUsageForApiKey(Long apiKeyId) {
        return logRepo.findByApiKeyId(apiKeyId)
                .stream().map(this::convert).collect(Collectors.toList());
    }

    @Override
    public List<ApiUsageLogDto> getUsageForToday(Long apiKeyId) {
        Timestamp start = Timestamp.valueOf(LocalDate.now().atStartOfDay());
        Timestamp end = Timestamp.valueOf(LocalDate.now().plusDays(1).atStartOfDay());
        return logRepo.findByApiKeyIdAndTimestampBetween(apiKeyId, start, end)
                .stream().map(this::convert).collect(Collectors.toList());
    }

    @Override
    public long countRequestsToday(Long apiKeyId) {
        Timestamp start = Timestamp.valueOf(LocalDate.now().atStartOfDay());
        Timestamp end = Timestamp.valueOf(LocalDate.now().plusDays(1).atStartOfDay());
        return logRepo.countByApiKeyIdAndTimestampBetween(apiKeyId, start, end);
    }

    private ApiUsageLogDto convert(ApiUsageLog log) {
        ApiUsageLogDto dto = new ApiUsageLogDto();
        dto.setId(log.getId());
        dto.setApiKeyId(log.getApiKey().getId());
        dto.setEndpoint(log.getEndpoint());
        dto.setTimestamp(log.getTimestamp());
        return dto;
    }
}
