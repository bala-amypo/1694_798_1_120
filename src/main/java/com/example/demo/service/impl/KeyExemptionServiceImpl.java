package com.example.demo.service.impl;

import com.example.demo.entity.KeyExemption;
import com.example.demo.repository.KeyExemptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class KeyExemptionServiceImpl {

    @Autowired
    private KeyExemptionRepository keyExemptionRepo;

    public List<KeyExemption> getValidExemptions() {
        LocalDateTime now = LocalDateTime.now();
        List<KeyExemption> exemptions = keyExemptionRepo.findAll();
        exemptions.removeIf(exemption ->
                !exemption.getUnlimitedAccess() &&
                exemption.getTemporaryExtensionLimit() == 0 &&
                exemption.getValidUntil().isBefore(now) // changed before() to isBefore()
        );
        return exemptions;
    }
}
