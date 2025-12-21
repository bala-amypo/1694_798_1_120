package com.example.demo.repository;

import com.example.demo.entity.ApiUsageLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface ApiUsageLogRepository extends JpaRepository<ApiUsageLog, Long> {

    List<ApiUsageLog> findByApiKeyId(Long id);

    List<ApiUsageLog> findByApiKeyIdAndTimestampBetween(Long id, Timestamp start, Timestamp end);

    long countByApiKeyIdAndTimestampBetween(Long id, Timestamp start, Timestamp end);
}
