package com.example.demo.service;

import com.example.demo.dto.ApiUsageLogDto;
import java.util.List;

public interface ApiUsageLogService {
    ApiUsageLogDto createApiUsageLog(ApiUsageLogDto dto);
    List<ApiUsageLogDto> getUsageLogsByApiKey(Long apiKeyId);
    List<ApiUsageLogDto> getAllUsageLogs();
    void deleteUsageLog(Long id);
}
