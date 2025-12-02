package com.opinix.backend.dto;

public class RegisterRequest {
    private String email;
    private String password;
    private String fullName;

    //TODO getters and setters
    public String getPassword(){
        return password;
    }

    public String getEmail(){
        return email;
    }

    public String getFullName(){
        return fullName;
    }
}
