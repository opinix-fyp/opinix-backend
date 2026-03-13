package com.opinix.backend.dto;

public class RegisterRequest {
    private String email;
    private String password;
    private String fullName;

    public String getPassword(){
        return password;
    }

    private void setPassword(String password){
        this.password = password;
    }

    public String getEmail(){
        return email;
    }

    private void setEmail(String email){
        this.email = email;
    }

    public String getFullName(){
        return fullName;
    }

    private void setFullName(String fullName){
        this.fullName = fullName;
    }
}
