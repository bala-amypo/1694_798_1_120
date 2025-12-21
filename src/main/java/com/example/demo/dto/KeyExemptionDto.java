package com.example.demo.dto;

import java.sql.Timestamp;

public class KeyExemptionDto {
    private Long id;
    private Long apiKeyId;
    private String notes;
    private Boolean unlimitedAccess;
    private Integer temporaryExtensionLimit;
    private Timestamp validUntil;

    // getters & setters
}
