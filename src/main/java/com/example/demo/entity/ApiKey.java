package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class ApiKey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String keyValue;

    private boolean active;

    // 🔴 REQUIRED BY ApiKeyServiceImpl.getPlan()
    @ManyToOne
    @JoinColumn(name = "quota_plan_id")
    private QuotaPlan plan;

    // -------- getters & setters --------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    // 🔴 THIS FIXES getPlan()
    public QuotaPlan getPlan() {
        return plan;
    }

    public void setPlan(QuotaPlan plan) {
        this.plan = plan;
    }
}
