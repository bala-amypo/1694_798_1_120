package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class QuotaPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private int dailyLimit;
    private boolean active;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }  // fixes getDescription()
    public void setDescription(String description) { this.description = description; }

    public int getDailyLimit() { return dailyLimit; }
    public void setDailyLimit(int dailyLimit) { this.dailyLimit = dailyLimit; }

    public boolean isActive() { return active; }            // fixes isActive()
    public void setActive(boolean active) { this.active = active; }
}
