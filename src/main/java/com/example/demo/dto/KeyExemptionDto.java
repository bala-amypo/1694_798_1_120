package com.example.demo.dto;

import java.sql.Timestamp;

public class KeyExemptionDto {

    private Long id;
    private Long apiKeyId;
    private Integer temporaryExtensionLimit;
    private Timestamp validUntil;

    public KeyExemptionDto() {
    }

    public KeyExemptionDto(Long id, Long apiKeyId, Integer temporaryExtensionLimit, Timestamp validUntil) {
        this.id = id;
        this.apiKeyId = apiKeyId;
        this.temporaryExtensionLimit = temporaryExtensionLimit;
        this.validUntil = validUntil;
    }

    public Long getId() {
        return id;
    }

    public Long getApiKeyId() {
        return apiKeyId;
    }

    public Integer getTemporaryExtensionLimit() {
        return temporaryExtensionLimit;
    }

    public Timestamp getValidUntil() {
        return validUntil;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setApiKeyId(Long apiKeyId) {
        this.apiKeyId = apiKeyId;
    }

    public void setTemporaryExtensionLimit(Integer temporaryExtensionLimit) {
        this.temporaryExtensionLimit = temporaryExtensionLimit;
    }

    public void setValidUntil(Timestamp validUntil) {
        this.validUntil = validUntil;
    }
}
