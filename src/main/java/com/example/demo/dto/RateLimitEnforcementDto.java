package com.example.demo.dto;

import java.sql.Timestamp;

public class RateLimitEnforcementDto {
    private Long id;
    private Long apiKeyId;
    private Timestamp blockedAt;
    private Integer limitExceededBy;
    private String message;

    // getters & setters
}
