package com.example.demo.repository;

import com.example.demo.entity.QuotaPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuotaPlanRepository extends JpaRepository<QuotaPlan, Long> {

    // Make sure this matches the entity field exactly
    QuotaPlan findByPlanName(String planName);
}
