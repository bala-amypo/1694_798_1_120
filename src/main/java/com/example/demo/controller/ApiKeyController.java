package com.example.demo.controller;

import com.example.demo.entity.ApiKey;
import com.example.demo.service.ApiKeyService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/api-keys")
@Tag(name = "API Keys")
public class ApiKeyController {

    private final ApiKeyService service;

    public ApiKeyController(ApiKeyService service) {
        this.service = service;
    }

    @PostMapping
    public ApiKey create(@RequestBody ApiKey key) {
        return service.createApiKey(key);
    }

    @PutMapping("/{id}")
    public ApiKey update(@PathVariable Long id, @RequestBody ApiKey key) {
        return service.updateApiKey(id, key);
    }

    @GetMapping("/{id}")
    public ApiKey getById(@PathVariable Long id) {
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
