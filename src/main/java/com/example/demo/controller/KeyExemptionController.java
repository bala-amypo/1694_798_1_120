package com.example.demo.controller;

import com.example.demo.entity.KeyExemption;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/exemptions")
public class KeyExemptionController {

    private final List<KeyExemption> exemptions = new ArrayList<>();

    @GetMapping
    public List<KeyExemption> getAllExemptions() {
        return exemptions;
    }

    @PostMapping
    public KeyExemption addExemption(@RequestBody KeyExemption exemption) {
        exemptions.add(exemption);
        return exemption;
    }
}
