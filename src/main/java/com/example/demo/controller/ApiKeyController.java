package com.example.demo.controller;

import com.example.demo.dto.ApiKeyDto;
import com.example.demo.service.ApiKeyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/api-keys")
public class ApiKeyController {

    private final ApiKeyService apiKeyService;

    public ApiKeyController(ApiKeyService apiKeyService) {
        this.apiKeyService = apiKeyService;
    }

    @PostMapping
    public ResponseEntity<ApiKeyDto> createApiKey(@RequestBody ApiKeyDto dto) {
        ApiKeyDto response = apiKeyService.createApiKey(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiKeyDto> updateApiKey(@PathVariable Long id, @RequestBody ApiKeyDto dto) {
        ApiKeyDto response = apiKeyService.updateApiKey(id, dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiKeyDto> getApiKeyById(@PathVariable Long id) {
        ApiKeyDto response = apiKeyService.getApiKeyById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ApiKeyDto>> getAllApiKeys() {
        List<ApiKeyDto> list = apiKeyService.getAllApiKeys();
        return ResponseEntity.ok(list);
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateApiKey(@PathVariable Long id) {
        apiKeyService.deactivateApiKey(id);
        return ResponseEntity.noContent().build();
    }
}
