package com.opinix.backend.controller;

import com.opinix.backend.dto.HealthResponse;
import com.opinix.backend.service.HealthService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/health")
public class HealthController {
    private final HealthService healthService;

    public HealthController(HealthService healthService){
        this.healthService = healthService;
    }


    @GetMapping("/check")
    public HealthResponse healthCheck(){
        return healthService.getHealthStatus();
    }
}
