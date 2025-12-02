package com.opinix.backend.dto;

public class UserResponse { //class for what returns after registration
    private long id;
    private String email;
    private String fullName;

    //empty constructor
    public UserResponse(){}

    //getters
    public long getId(){
        return id;
    }
    public String getEmail(){
        return email;
    }
    public String getFullName(){
        return fullName;
    }

    //setters
    public void setId(long id){
        this.id = id;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setFullName(String fullName){
        this.fullName = fullName;
    }
}
