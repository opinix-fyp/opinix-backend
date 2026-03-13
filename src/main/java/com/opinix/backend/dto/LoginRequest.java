package com.opinix.backend.dto;

public class LoginRequest {
    public String email;
    public String password;

    public String getEmail(){
        return email;
    }

    private void setEmail(String email){
        this.email = email;
    }

    public String getPassword(){
        return password;
    }

    private void setPassword(String password){
        this.password = password;
    }
}
