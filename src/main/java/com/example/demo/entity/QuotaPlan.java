package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class QuotaPlan {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String planName;

    private Integer dailyLimit;
    private String description;
    private Boolean active = true;

    public Long getId() { return id; }
    public String getPlanName() { return planName; }
    public void setPlanName(String planName) { this.planName = planName; }
    public Integer getDailyLimit() { return dailyLimit; }
    public void setDailyLimit(Integer dailyLimit) { this.dailyLimit = dailyLimit; }
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
