package com.example.demo.controller;

import com.example.demo.entity.ApiUsageLog;
import com.example.demo.service.ApiUsageLogService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usage-logs")
@Tag(name = "Usage Logs")
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
    public List<ApiUsageLog> getToday(@PathVariable Long keyId) {
        return service.getUsageForToday(keyId);
    }

    @GetMapping("/key/{keyId}/count-today")
    public long countToday(@PathVariable Long keyId) {
        return service.countRequestsToday(keyId);
    }
}
