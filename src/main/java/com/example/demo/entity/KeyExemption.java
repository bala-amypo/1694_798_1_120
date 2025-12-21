package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import java.sql.Timestamp;

@Entity
@Table(name = "key_exemption")
public class KeyExemption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "api_key_id", nullable = false)
    private ApiKey apiKey;

    private String notes;

    private Boolean unlimitedAccess = false;

    @Min(0)
    private Integer temporaryExtensionLimit;

    @Column(nullable = false)
    private Timestamp validUntil;

    public KeyExemption() {}
}
