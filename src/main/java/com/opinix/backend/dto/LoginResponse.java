package com.opinix.backend.dto;

public class LoginResponse { //return object after login
    private String message;
    //later to add token, tokenType, etc

    public LoginResponse(){
        //empty constructor
    }

    public String getMessage(){
        return message;
    }

    public void setMessage(String message){
        this.message = message;
    }
}
