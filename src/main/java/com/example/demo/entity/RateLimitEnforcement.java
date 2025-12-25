package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "rate_limit_enforcements")
public class RateLimitEnforcement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer limitExceededBy;
    private String message;

    @ManyToOne
    private ApiKey apiKey;

    public Long getId() { return id; }

    public Integer getLimitExceededBy() { return limitExceededBy; }
    public void setLimitExceededBy(Integer limitExceededBy) {
        this.limitExceededBy = limitExceededBy;
    }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public ApiKey getApiKey() { return apiKey; }
    public void setApiKey(ApiKey apiKey) { this.apiKey = apiKey; }
}
