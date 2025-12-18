package com.example.demo.controller;

import com.example.demo.entity.RateLimitEnforcement;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/enforcements")
public class RateLimitEnforcementController {

    private final List<RateLimitEnforcement> enforcements = new ArrayList<>();

    @GetMapping
    public List<RateLimitEnforcement> getAllEnforcements() {
        return enforcements;
    }

    @PostMapping
    public RateLimitEnforcement addEnforcement(@RequestBody RateLimitEnforcement enforcement) {
        enforcements.add(enforcement);
        return enforcement;
    }
}
