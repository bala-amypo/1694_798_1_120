package com.example.demo.repository;

import com.example.demo.entity.ApiUsageLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;

public interface ApiUsageLogRepository extends JpaRepository<ApiUsageLog, Long> {

    List<ApiUsageLog> findByApiKey_Id(Long id);

    @Query("SELECT u FROM ApiUsageLog u WHERE u.apiKey.id = :keyId AND u.timestamp BETWEEN :start AND :end")
    List<ApiUsageLog> findForKeyBetween(
            @Param("keyId") Long keyId,
            @Param("start") Instant start,
            @Param("end") Instant end
    );

    @Query("SELECT COUNT(u) FROM ApiUsageLog u WHERE u.apiKey.id = :keyId AND u.timestamp BETWEEN :start AND :end")
    int countForKeyBetween(
            @Param("keyId") Long keyId,
            @Param("start") Instant start,
            @Param("end") Instant end
    );
}
