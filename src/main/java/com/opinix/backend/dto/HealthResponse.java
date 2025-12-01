package com.opinix.backend.dto;

public class HealthResponse {
    private String status, message;

    public HealthResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus(){
        return status;
    }

    public String getMessage(){
        return message;
    }
}
