package com.example.demo.service;

import com.example.demo.dto.ApiUsageLogDto;
import java.util.List;

public interface ApiUsageLogService {

    ApiUsageLogDto logUsage(ApiUsageLogDto dto);

    List<ApiUsageLogDto> getUsageForApiKey(Long apiKeyId);

    List<ApiUsageLogDto> getUsageForToday(Long apiKeyId);

    long countRequestsToday(Long apiKeyId);
}
