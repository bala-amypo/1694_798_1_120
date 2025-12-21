package com.example.demo.service;

import com.example.demo.dto.QuotaPlanDto;
import java.util.List;

public interface QuotaPlanService {

    QuotaPlanDto createPlan(QuotaPlanDto dto);

    QuotaPlanDto updatePlan(Long id, QuotaPlanDto dto);

    List<QuotaPlanDto> getAllPlans();

    void deactivateQuotaPlan(Long id);
}
