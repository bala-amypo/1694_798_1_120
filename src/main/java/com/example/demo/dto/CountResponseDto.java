package com.example.demo.dto;

public class CountResponseDto {
    private Long count;

    public CountResponseDto(Long count) {
        this.count = count;
    }

    public Long getCount() { return count; }
    public void setCount(Long count) { this.count = count; }
}
