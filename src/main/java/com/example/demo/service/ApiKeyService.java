package com.example.demo.service;

import com.example.demo.dto.ApiKeyDto;
import java.util.List;

public interface ApiKeyService {

    ApiKeyDto create(ApiKeyDto dto);

    ApiKeyDto update(Long id, ApiKeyDto dto);

    ApiKeyDto getById(Long id);

    List<ApiKeyDto> getAll();

    void delete(Long id);
}
