package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "key_exemptions")
public class KeyExemption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer temporaryExtensionLimit;
    private Instant validUntil;

    @ManyToOne
    private ApiKey apiKey;

    public KeyExemption() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {      // ✅ added (safe)
        this.id = id;
    }

    public Integer getTemporaryExtensionLimit() {
        return temporaryExtensionLimit;
    }

    public void setTemporaryExtensionLimit(Integer temporaryExtensionLimit) {
        this.temporaryExtensionLimit = temporaryExtensionLimit;
    }

    public Instant getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Instant validUntil) {
        this.validUntil = validUntil;
    }

    public ApiKey getApiKey() {
        return apiKey;
    }

    public void setApiKey(ApiKey apiKey) {
        this.apiKey = apiKey;
    }
}
