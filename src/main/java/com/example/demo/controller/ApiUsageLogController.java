package com.example.demo.controller;

import com.example.demo.entity.ApiUsageLog;
import com.example.demo.service.ApiUsageLogService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usage-logs")
public class ApiUsageLogController {

    private final ApiUsageLogService service;

    public ApiUsageLogController(ApiUsageLogService service) {
        this.service = service;
    }

    @PostMapping
    public ApiUsageLog log(@RequestBody ApiUsageLog log) {
        return service.logUsage(log);
    }

    @GetMapping("/key/{keyId}")
    public List<ApiUsageLog> getForKey(@PathVariable Long keyId) {
        return service.getUsageForApiKey(keyId);
    }

    @GetMapping("/key/{keyId}/today")
    public List<ApiUsageLog> getForToday(@PathVariable Long keyId) {
        return service.getUsageForToday(keyId);
    }

    @GetMapping("/key/{keyId}/count-today")
    public int countToday(@PathVariable Long keyId) {
        return service.countRequestsToday(keyId);
    }
}
