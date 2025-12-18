package com.example.demo.controller;

import com.example.demo.model.ApiKey;
import com.example.demo.service.ApiKeyService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/api-keys")
@Tag(name = "API Keys", description = "Manage API keys")
public class ApiKeyController {

    private final ApiKeyService apiKeyService;

    public ApiKeyController(ApiKeyService apiKeyService) {
        this.apiKeyService = apiKeyService;
    }

    @PostMapping
    public ResponseEntity<ApiKey> createApiKey(@RequestBody ApiKey apiKey) {
        return ResponseEntity.ok(apiKeyService.createApiKey(apiKey));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiKey> updateApiKey(@PathVariable Long id, @RequestBody ApiKey apiKey) {
        return ResponseEntity.ok(apiKeyService.updateApiKey(id, apiKey));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiKey> getApiKeyById(@PathVariable Long id) {
        return ResponseEntity.ok(apiKeyService.getApiKeyById(id));
    }

    @GetMapping
    public ResponseEntity<List<ApiKey>> getAllApiKeys() {
        return ResponseEntity.ok(apiKeyService.getAllApiKeys());
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<ApiKey> deactivateApiKey(@PathVariable Long id) {
        return ResponseEntity.ok(apiKeyService.deactivateApiKey(id));
    }
}
