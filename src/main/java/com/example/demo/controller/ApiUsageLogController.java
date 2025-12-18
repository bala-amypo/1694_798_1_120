package com.example.demo.controller;

import com.example.demo.entity.ApiUsageLog;
import com.example.demo.service.ApiUsageLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usage")
public class ApiUsageLogController {

    @Autowired
    private ApiUsageLogService apiUsageLogService;

    @GetMapping
    public List<ApiUsageLog> getAllLogs() {
        return apiUsageLogService.getAllLogs();
    }

    @GetMapping("/{id}")
    public ApiUsageLog getLog(@PathVariable Long id) {
        return apiUsageLogService.getLogById(id);
    }

    @PostMapping
    public ApiUsageLog createLog(@RequestBody ApiUsageLog log) {
        return apiUsageLogService.createLog(log);
    }

    @DeleteMapping("/{id}")
    public void deleteLog(@PathVariable Long id) {
        apiUsageLogService.deleteLog(id);
    }
}
