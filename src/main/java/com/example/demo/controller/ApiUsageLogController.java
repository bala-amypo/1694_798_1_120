package com.example.demo.controller;

import com.example.demo.dto.ApiUsageLogDto;
import com.example.demo.dto.CountResponseDto;
import com.example.demo.service.ApiUsageLogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usage-logs")
public class ApiUsageLogController {

    private final ApiUsageLogService usageLogService;

    public ApiUsageLogController(ApiUsageLogService usageLogService) {
        this.usageLogService = usageLogService;
    }

    @PostMapping
    public ResponseEntity<ApiUsageLogDto> logUsage(@RequestBody ApiUsageLogDto dto) {
        ApiUsageLogDto saved = usageLogService.logUsage(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/key/{keyId}")
    public ResponseEntity<List<ApiUsageLogDto>> getUsageForApiKey(@PathVariable Long keyId) {
        List<ApiUsageLogDto> list = usageLogService.getUsageForApiKey(keyId);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/key/{keyId}/today")
    public ResponseEntity<List<ApiUsageLogDto>> getUsageForToday(@PathVariable Long keyId) {
        List<ApiUsageLogDto> list = usageLogService.getUsageForToday(keyId);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/key/{keyId}/count-today")
    public ResponseEntity<CountResponseDto> countRequestsToday(@PathVariable Long keyId) {
        Long count = usageLogService.countRequestsToday(keyId);
        return ResponseEntity.ok(new CountResponseDto(count));
    }
}
