package com.example.demo.dto;

import java.sql.Timestamp;

public class ApiKeyDto {
    private Long id;
    private String keyValue;
    private Long ownerId;
    private Long planId;
    private Boolean active;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    // getters & setters
}
