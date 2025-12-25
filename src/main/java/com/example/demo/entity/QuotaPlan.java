package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "quota_plans")
public class QuotaPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String planName;
    private Integer dailyLimit;
    private boolean active = true;

    public Long getId() { return id; }

    public String getPlanName() { return planName; }
    public void setPlanName(String planName) { this.planName = planName; }

    public Integer getDailyLimit() { return dailyLimit; }
    public void setDailyLimit(Integer dailyLimit) { this.dailyLimit = dailyLimit; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
