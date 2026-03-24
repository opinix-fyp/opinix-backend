package com.opinix.backend.service;

import com.opinix.backend.dto.HealthResponse;
import org.springframework.stereotype.Service;

@Service
public class HealthService {
    public HealthResponse getHealthStatus(){
        // to expand later
        return new HealthResponse("OK", "Backend is running");
    }
}
