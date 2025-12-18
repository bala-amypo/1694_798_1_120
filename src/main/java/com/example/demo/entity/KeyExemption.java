package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class KeyExemption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean unlimitedAccess;
    private int temporaryExtensionLimit;
    private LocalDateTime validUntil;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public boolean getUnlimitedAccess() { return unlimitedAccess; }
    public void setUnlimitedAccess(boolean unlimitedAccess) { this.unlimitedAccess = unlimitedAccess; }

    public int getTemporaryExtensionLimit() { return temporaryExtensionLimit; }
    public void setTemporaryExtensionLimit(int temporaryExtensionLimit) { this.temporaryExtensionLimit = temporaryExtensionLimit; }

    public LocalDateTime getValidUntil() { return validUntil; }
    public void setValidUntil(LocalDateTime validUntil) { this.validUntil = validUntil; }

    // Additional helpers for service logic
    public boolean isExpired() {
        return validUntil != null && validUntil.isBefore(LocalDateTime.now());
    }
}
