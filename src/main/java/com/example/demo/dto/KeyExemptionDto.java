package com.example.demo.dto;

import java.sql.Timestamp;

public class KeyExemptionDto {
    private Long id;
    private Long apiKeyId;
    private Boolean unlimitedAccess;
    private Integer temporaryExtensionLimit;
    private Timestamp validUntil;
    private String notes;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getApiKeyId() { return apiKeyId; }
    public void setApiKeyId(Long apiKeyId) { this.apiKeyId = apiKeyId; }

    public Boolean getUnlimitedAccess() { return unlimitedAccess; }
    public void setUnlimitedAccess(Boolean unlimitedAccess) { this.unlimitedAccess = unlimitedAccess; }

    public Integer getTemporaryExtensionLimit() { return temporaryExtensionLimit; }
    public void setTemporaryExtensionLimit(Integer temporaryExtensionLimit) { this.temporaryExtensionLimit = temporaryExtensionLimit; }

    public Timestamp getValidUntil() { return validUntil; }
    public void setValidUntil(Timestamp validUntil) { this.validUntil = validUntil; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
