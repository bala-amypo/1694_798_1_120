package com.example.demo.controller;

import com.example.demo.model.ApiUsageLog;
import com.example.demo.service.ApiUsageLogService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usage-logs")
@Tag(name = "API Usage Logs", description = "Log and view API usage")
public class ApiUsageLogController {

    private final ApiUsageLogService usageLogService;

    public ApiUsageLogController(ApiUsageLogService usageLogService) {
        this.usageLogService = usageLogService;
    }

    @PostMapping
    public ResponseEntity<ApiUsageLog> logUsage(@RequestBody ApiUsageLog log) {
        return ResponseEntity.ok(usageLogService.logUsage(log));
    }

    @GetMapping("/key/{keyId}")
    public ResponseEntity<List<ApiUsageLog>> getUsageForKey(@PathVariable Long keyId) {
        return ResponseEntity.ok(usageLogService.getUsageForApiKey(keyId));
    }

    @GetMapping("/key/{keyId}/today")
    public ResponseEntity<List<ApiUsageLog>> getTodayUsage(@PathVariable Long keyId) {
        return ResponseEntity.ok(usageLogService.getUsageForToday(keyId));
    }

    @GetMapping("/key/{keyId}/count-today")
    public ResponseEntity<Long> countRequestsToday(@PathVariable Long keyId) {
        return ResponseEntity.ok(usageLogService.countRequestsToday(keyId));
    }
}
