package com.example.demo.controller;

import com.example.demo.entity.ApiKey;
import com.example.demo.service.ApiKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/keys")
public class ApiKeyController {

    @Autowired
    private ApiKeyService apiKeyService;

    @GetMapping
    public List<ApiKey> getAllKeys() {
        return apiKeyService.getAllKeys();
    }

    @GetMapping("/{id}")
    public ApiKey getKey(@PathVariable Long id) {
        return apiKeyService.getKeyById(id);
    }

    @PostMapping
    public ApiKey createKey(@RequestBody ApiKey apiKey) {
        return apiKeyService.createKey(apiKey);
    }

    @PutMapping("/{id}")
    public ApiKey updateKey(@PathVariable Long id, @RequestBody ApiKey apiKey) {
        return apiKeyService.updateKey(id, apiKey);
    }

    @DeleteMapping("/{id}")
    public void deleteKey(@PathVariable Long id) {
        apiKeyService.deleteKey(id);
    }
}
