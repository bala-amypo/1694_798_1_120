package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "key_exemptions")
public class KeyExemption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "api_key_id", nullable = false)
    private ApiKey apiKey;

    private String notes;

    @Column(nullable = false)
    private Boolean unlimitedAccess = false;

    private Integer temporaryExtensionLimit;

    private LocalDateTime validUntil;

    // No-arg constructor
    public KeyExemption() {
    }

    // Parameterized constructor
    public KeyExemption(ApiKey apiKey, String notes,
                        Boolean unlimitedAccess,
                        Integer temporaryExtensionLimit,
                        LocalDateTime validUntil) {
        this.apiKey = apiKey;
        this.notes = notes;
        this.unlimitedAccess = unlimitedAccess;
        this.temporaryExtensionLimit = temporaryExtensionLimit;
        this.validUntil = validUntil;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public ApiKey getApiKey() {
        return apiKey;
    }

    public String getNotes() {
        return notes;
    }

    public Boolean getUnlimitedAccess() {
        return unlimitedAccess;
    }

    public Integer getTemporaryExtensionLimit() {
        return temporaryExtensionLimit;
    }

    public LocalDateTime getValidUntil() {
        return validUntil;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setApiKey(ApiKey apiKey) {
        this.apiKey = apiKey;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setUnlimitedAccess(Boolean unlimitedAccess) {
        this.unlimitedAccess = unlimitedAccess;
    }

    public void setTemporaryExtensionLimit(Integer temporaryExtensionLimit) {
        this.temporaryExtensionLimit = temporaryExtensionLimit;
    }

    public void setValidUntil(LocalDateTime validUntil) {
        this.validUntil = validUntil;
    }
}
