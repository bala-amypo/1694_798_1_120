package com.example.demo.repository;

import com.example.demo.entity.KeyExemption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeyExemptionRepository extends JpaRepository<KeyExemption, Long> {

    // This now WORKS because apiKey exists
    KeyExemption findByApiKey_Id(Long apiKeyId);
}
