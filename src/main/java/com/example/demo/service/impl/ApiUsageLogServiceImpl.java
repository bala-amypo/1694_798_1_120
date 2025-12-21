package com.example.demo.service.impl;

import com.example.demo.dto.ApiUsageLogDto;
import com.example.demo.entity.ApiKey;
import com.example.demo.entity.ApiUsageLog;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.ApiUsageLogRepository;
import com.example.demo.service.ApiUsageLogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApiUsageLogServiceImpl implements ApiUsageLogService {

    @Autowired
    private ApiUsageLogRepository logRepo;

    @Autowired
    private ApiKeyRepository keyRepo;

    @Override
    public ApiUsageLogDto createApiUsageLog(ApiUsageLogDto dto) {
        ApiKey apiKey = keyRepo.findById(dto.getApiKeyId()).orElseThrow();
        ApiUsageLog log = new ApiUsageLog();
        log.setApiKey(apiKey);
        log.setEndpoint(dto.getEndpoint());
        log.setTimestamp(new Timestamp(System.currentTimeMillis()));
        ApiUsageLog saved = logRepo.save(log);
        return convert(saved);
    }

    @Override
    public List<ApiUsageLogDto> getUsageLogsByApiKey(Long apiKeyId) {
        return logRepo.findByApiKeyId(apiKeyId).stream().map(this::convert).collect(Collectors.toList());
    }

    @Override
    public List<ApiUsageLogDto> getAllUsageLogs() {
        return logRepo.findAll().stream().map(this::convert).collect(Collectors.toList());
    }

    @Override
    public void deleteUsageLog(Long id) {
        ApiUsageLog log = logRepo.findById(id).orElseThrow();
        logRepo.delete(log);
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
