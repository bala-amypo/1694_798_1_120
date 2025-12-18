package com.example.demo.repository;

import com.example.demo.entity.QuotaPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuotaPlanRepository extends JpaRepository<QuotaPlan, Long> {

    // Update method name to match entity field
    QuotaPlan findByName(String name);  // <-- 'name' matches QuotaPlan.name
}
