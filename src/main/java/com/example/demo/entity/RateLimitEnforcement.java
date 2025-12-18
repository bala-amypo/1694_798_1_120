package com.example.demo.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
public class RateLimitEnforcement {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private ApiKey apiKey;

    private Timestamp blockedAt;
    private Integer limitExceededBy;
    private String message;

    public Long getId() { return id; }
    public ApiKey getApiKey() { return apiKey; }
    public void setApiKey(ApiKey apiKey) { this.apiKey = apiKey; }
    public Integer getLimitExceededBy() { return limitExceededBy; }
    public void setLimitExceededBy(Integer limitExceededBy) { this.limitExceededBy = limitExceededBy; }
    public Timestamp getBlockedAt() { return blockedAt; }
    public void setBlockedAt(Timestamp blockedAt) { this.blockedAt = blockedAt; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
