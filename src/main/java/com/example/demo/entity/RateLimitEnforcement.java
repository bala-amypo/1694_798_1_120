package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "rate_limit_enforcements")
public class RateLimitEnforcement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "api_key_id", nullable = false)
    private ApiKey apiKey;

    @Column(nullable = false)
    private LocalDateTime blockedAt;

    @Column(nullable = false)
    private Integer limitExceededBy;

    @Column(nullable = false)
    private String message;

    // No-arg constructor
    public RateLimitEnforcement() {
    }

    // Parameterized constructor
    public RateLimitEnforcement(ApiKey apiKey, LocalDateTime blockedAt,
                                Integer limitExceededBy, String message) {
        this.apiKey = apiKey;
        this.blockedAt = blockedAt;
        this.limitExceededBy = limitExceededBy;
        this.message = message;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public ApiKey getApiKey() {
        return apiKey;
    }

    public LocalDateTime getBlockedAt() {
        return blockedAt;
    }

    public Integer getLimitExceededBy() {
        return limitExceededBy;
    }

    public String getMessage() {
        return message;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setApiKey(ApiKey apiKey) {
        this.apiKey = apiKey;
    }

    public void setBlockedAt(LocalDateTime blockedAt) {
        this.blockedAt = blockedAt;
    }

    public void setLimitExceededBy(Integer limitExceededBy) {
        this.limitExceededBy = limitExceededBy;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
