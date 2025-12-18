package com.example.demo.service.impl;

import com.example.demo.entity.ApiUsageLog;
import com.example.demo.repository.ApiUsageLogRepository;
import com.example.demo.service.ApiUsageLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApiUsageLogServiceImpl implements ApiUsageLogService {

    @Autowired
    private ApiUsageLogRepository apiUsageLogRepository;

    @Override
    public List<ApiUsageLog> getAllLogs() {
        return apiUsageLogRepository.findAll();
    }

    @Override
    public ApiUsageLog getLogById(Long id) {
        return apiUsageLogRepository.findById(id).orElse(null);
    }

    @Override
    public ApiUsageLog createLog(ApiUsageLog log) {
        return apiUsageLogRepository.save(log);
    }

    @Override
    public void deleteLog(Long id) {
        apiUsageLogRepository.deleteById(id);
    }
}
