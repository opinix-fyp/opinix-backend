package com.opinix.backend.dto;

public class UserResponse { //class for what returns after registration
    private Long id;
    private String email;
    private String fullName;

    //empty constructor
    public UserResponse(){}

    //getters
    public Long getId(){
        return id;
    }
    public String getEmail(){
        return email;
    }
    public String getFullName(){
        return fullName;
    }

    //setters
    public void setId(Long id){
        this.id = id;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setFullName(String fullName){
        this.fullName = fullName;
    }
}
