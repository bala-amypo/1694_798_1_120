package com.example.demo.service.impl;

import com.example.demo.dto.ApiKeyDto;
import com.example.demo.entity.ApiKey;
import com.example.demo.entity.QuotaPlan;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.QuotaPlanRepository;
import com.example.demo.service.ApiKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApiKeyServiceImpl implements ApiKeyService {

    @Autowired
    private ApiKeyRepository apiKeyRepository;

    @Autowired
    private QuotaPlanRepository quotaPlanRepository;

    @Override
    public ApiKeyDto create(ApiKeyDto dto) {

        QuotaPlan plan = quotaPlanRepository.findById(dto.getPlanId())
                .orElseThrow(() -> new ResourceNotFoundException("Plan not found"));

        ApiKey key = new ApiKey();
        key.setKeyValue(dto.getKeyValue());
        key.setOwnerId(dto.getOwnerId());
        key.setPlan(plan);
        key.setActive(dto.getActive());
        key.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        key.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        ApiKey saved = apiKeyRepository.save(key);

        ApiKeyDto response = new ApiKeyDto();
        response.setId(saved.getId());
        response.setKeyValue(saved.getKeyValue());
        response.setOwnerId(saved.getOwnerId());
        response.setPlanId(saved.getPlan().getId());
        response.setActive(saved.getActive());
        response.setCreatedAt(saved.getCreatedAt());
        response.setUpdatedAt(saved.getUpdatedAt());

        return response;
    }

    @Override
    public ApiKeyDto update(Long id, ApiKeyDto dto) {

        ApiKey key = apiKeyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("API Key not found"));

        if (dto.getPlanId() != null) {
            QuotaPlan plan = quotaPlanRepository.findById(dto.getPlanId())
                    .orElseThrow(() -> new ResourceNotFoundException("Plan not found"));
            key.setPlan(plan);
        }

        if (dto.getKeyValue() != null) key.setKeyValue(dto.getKeyValue());
        if (dto.getActive() != null) key.setActive(dto.getActive());

        key.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        ApiKey saved = apiKeyRepository.save(key);

        ApiKeyDto response = new ApiKeyDto();
        response.setId(saved.getId());
        response.setKeyValue(saved.getKeyValue());
        response.setOwnerId(saved.getOwnerId());
        response.setPlanId(saved.getPlan().getId());
        response.setActive(saved.getActive());
        response.setCreatedAt(saved.getCreatedAt());
        response.setUpdatedAt(saved.getUpdatedAt());

        return response;
    }

    @Override
    public ApiKeyDto getById(Long id) {
        ApiKey key = apiKeyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("API Key not found"));

        ApiKeyDto dto = new ApiKeyDto();
        dto.setId(key.getId());
        dto.setKeyValue(key.getKeyValue());
        dto.setOwnerId(key.getOwnerId());
        dto.setPlanId(key.getPlan().getId());
        dto.setActive(key.getActive());
        dto.setCreatedAt(key.getCreatedAt());
        dto.setUpdatedAt(key.getUpdatedAt());

        return dto;
    }

    @Override
    public List<ApiKeyDto> getAll() {
        return apiKeyRepository.findAll()
                .stream()
                .map(key -> {
                    ApiKeyDto dto = new ApiKeyDto();
                    dto.setId(key.getId());
                    dto.setKeyValue(key.getKeyValue());
                    dto.setOwnerId(key.getOwnerId());
                    dto.setPlanId(key.getPlan().getId());
                    dto.setActive(key.getActive());
                    dto.setCreatedAt(key.getCreatedAt());
                    dto.setUpdatedAt(key.getUpdatedAt());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        ApiKey key = apiKeyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("API Key not found"));

        apiKeyRepository.delete(key);
    }
}
