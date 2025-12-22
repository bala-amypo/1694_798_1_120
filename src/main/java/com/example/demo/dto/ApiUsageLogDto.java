package com.example.demo.dto;

import java.sql.Timestamp;

public class ApiUsageLogDto {
    private Long id;
    private Long apiKeyId;
    private String endpoint;
    private Timestamp timestamp;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getApiKeyId() { return apiKeyId; }
    public void setApiKeyId(Long apiKeyId) { this.apiKeyId = apiKeyId; }

    public String getEndpoint() { return endpoint; }
    public void setEndpoint(String endpoint) { this.endpoint = endpoint; }

    public Timestamp getTimestamp() { return timestamp; }
    public void setTimestamp(Timestamp timestamp) { this.timestamp = timestamp; }
}
