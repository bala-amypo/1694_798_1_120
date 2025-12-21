package com.example.demo.dto;

import java.sql.Timestamp;

public class RateLimitEnforcementDto {
    private Long id;
    private Long apiKeyId;
    private Timestamp blockedAt;
    private Integer limitExceededBy;
    private String message;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getApiKeyId() { return apiKeyId; }
    public void setApiKeyId(Long apiKeyId) { this.apiKeyId = apiKeyId; }

    public Timestamp getBlockedAt() { return blockedAt; }
    public void setBlockedAt(Timestamp blockedAt) { this.blockedAt = blockedAt; }

    public Integer getLimitExceededBy() { return limitExceededBy; }
    public void setLimitExceededBy(Integer limitExceededBy) { this.limitExceededBy = limitExceededBy; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
