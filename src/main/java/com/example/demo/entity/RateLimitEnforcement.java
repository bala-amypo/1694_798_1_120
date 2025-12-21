package com.example.demo.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "rate_limit_enforcements")
public class RateLimitEnforcement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Timestamp blockedAt;

    private Integer limitExceededBy;

    private String message;

    @ManyToOne
    @JoinColumn(name = "api_key_id")
    private ApiKey apiKey;

    public Long getId() { return id; }

    public Timestamp getBlockedAt() { return blockedAt; }
    public void setBlockedAt(Timestamp blockedAt) { this.blockedAt = blockedAt; }

    public Integer getLimitExceededBy() { return limitExceededBy; }
    public void setLimitExceededBy(Integer limitExceededBy) { this.limitExceededBy = limitExceededBy; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public ApiKey getApiKey() { return apiKey; }
    public void setApiKey(ApiKey apiKey) { this.apiKey = apiKey; }
}
