package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "api_usage_logs")
public class ApiUsageLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "api_key_id", nullable = false)
    private ApiKey apiKey;

    @Column(nullable = false)
    private String endpoint;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    // No-arg constructor
    public ApiUsageLog() {
    }

    // Parameterized constructor
    public ApiUsageLog(ApiKey apiKey, String endpoint, LocalDateTime timestamp) {
        this.apiKey = apiKey;
        this.endpoint = endpoint;
        this.timestamp = timestamp;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public ApiKey getApiKey() {
        return apiKey;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setApiKey(ApiKey apiKey) {
        this.apiKey = apiKey;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
