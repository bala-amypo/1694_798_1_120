package com.example.demo.service;

import com.example.demo.entity.ApiUsageLog;

import java.util.List;

public interface ApiUsageLogService {
    List<ApiUsageLog> getAllLogs();
    ApiUsageLog getLogById(Long id);
    ApiUsageLog createLog(ApiUsageLog log);
    void deleteLog(Long id);
}
