package com.example.demo.repository;

import com.example.demo.entity.ApiUsageLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.Instant;
import java.util.List;

public interface ApiUsageLogRepository extends JpaRepository<ApiUsageLog, Long> {

    List<ApiUsageLog> findByApiKey_Id(Long id);

    @Query("SELECT l FROM ApiUsageLog l WHERE l.apiKey.id = :keyId AND l.timestamp BETWEEN :start AND :end")
    List<ApiUsageLog> findForKeyBetween(Long keyId, Instant start, Instant end);

    @Query("SELECT COUNT(l) FROM ApiUsageLog l WHERE l.apiKey.id = :keyId AND l.timestamp BETWEEN :start AND :end")
    int countForKeyBetween(Long keyId, Instant start, Instant end);
}
