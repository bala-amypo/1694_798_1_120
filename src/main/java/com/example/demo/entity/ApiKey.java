package com.example.demo.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
public class ApiKey {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String keyValue;

    private Long ownerId;

    @ManyToOne
    private QuotaPlan plan;

    private Boolean active = true;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Long getId() { return id; }
    public String getKeyValue() { return keyValue; }
    public void setKeyValue(String keyValue) { this.keyValue = keyValue; }
    public QuotaPlan getPlan() { return plan; }
    public void setPlan(QuotaPlan plan) { this.plan = plan; }
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
