package com.example.demo.controller;

import com.example.demo.entity.ApiKey;
import com.example.demo.service.ApiKeyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/api-keys")
public class ApiKeyController {

    private final ApiKeyService service;

    public ApiKeyController(ApiKeyService service) {
        this.service = service;
    }

    @PostMapping
    public ApiKey create(@RequestBody ApiKey apiKey) {
        return service.createApiKey(apiKey);
    }

    @PutMapping("/{id}")
    public ApiKey update(@PathVariable Long id, @RequestBody ApiKey apiKey) {
        return service.updateApiKey(id, apiKey);
    }

    @GetMapping("/{id}")
    public ApiKey get(@PathVariable Long id) {
        return service.getApiKeyById(id);
    }

    @GetMapping
    public List<ApiKey> getAll() {
        return service.getAllApiKeys();
    }

    @PutMapping("/{id}/deactivate")
    public void deactivate(@PathVariable Long id) {
        service.deactivateApiKey(id);
    }
}
